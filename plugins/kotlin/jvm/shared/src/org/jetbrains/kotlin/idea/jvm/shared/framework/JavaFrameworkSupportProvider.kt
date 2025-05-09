// Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

package org.jetbrains.kotlin.idea.jvm.shared.framework

import com.intellij.framework.FrameworkTypeEx
import com.intellij.framework.addSupport.FrameworkSupportInModuleConfigurable
import com.intellij.framework.addSupport.FrameworkSupportInModuleProvider
import com.intellij.ide.util.frameworkSupport.FrameworkSupportModel
import com.intellij.openapi.module.JavaModuleType
import com.intellij.openapi.module.Module
import com.intellij.openapi.module.ModuleType
import com.intellij.openapi.roots.ModifiableModelsProvider
import com.intellij.openapi.roots.ModifiableRootModel
import com.intellij.openapi.roots.ui.configuration.FacetsProvider
import com.intellij.openapi.roots.ui.configuration.libraries.CustomLibraryDescription
import org.jetbrains.kotlin.idea.configuration.BuildSystemType
import org.jetbrains.kotlin.idea.configuration.buildSystemType
import org.jetbrains.kotlin.idea.formatter.KotlinStyleGuideCodeStyle
import org.jetbrains.kotlin.idea.formatter.ProjectCodeStyleImporter
import org.jetbrains.kotlin.idea.jvm.shared.KotlinJvmBundle
import org.jetbrains.kotlin.idea.projectConfiguration.JSLibraryStdDescription
import org.jetbrains.kotlin.idea.projectConfiguration.JavaRuntimeLibraryDescription
import org.jetbrains.kotlin.idea.statistics.WizardStatsService
import org.jetbrains.kotlin.idea.statistics.WizardStatsService.ProjectCreationStats
import javax.swing.JComponent

class JavaFrameworkSupportProvider : FrameworkSupportInModuleProvider() {
    override fun getFrameworkType(): FrameworkTypeEx = JavaFrameworkType.instance

    override fun createConfigurable(model: FrameworkSupportModel): FrameworkSupportInModuleConfigurable {
        return object : FrameworkSupportInModuleConfigurable() {
            private var description: JavaRuntimeLibraryDescription? = null

            override fun createLibraryDescription(): CustomLibraryDescription? {
                description = JavaRuntimeLibraryDescription(model.project)
                return description
            }

            override fun createComponent(): JComponent? = null

            override fun isOnlyLibraryAdded(): Boolean = true

            override fun addSupport(
                module: Module,
                rootModel: ModifiableRootModel,
                modifiableModelsProvider: ModifiableModelsProvider
            ) {
                FrameworksCompatibilityUtils.suggestRemoveIncompatibleFramework(
                    rootModel,
                    JSLibraryStdDescription.SUITABLE_LIBRARY_KINDS,
                    KotlinJvmBundle.message("presentable.name.kotlin.js")
                )

                description!!.finishLibConfiguration(module, rootModel, false)

                val isNewProject = model.project == null
                if (isNewProject) {
                    ProjectCodeStyleImporter.apply(module.project, KotlinStyleGuideCodeStyle.INSTANCE)
                }
                val projectCreationStats = ProjectCreationStats("Java", "Kotlin/JVM", "jps")
                WizardStatsService.logDataOnProjectGenerated(session = null, module.project, projectCreationStats)
            }

            override fun onFrameworkSelectionChanged(selected: Boolean) {
                if (selected) {
                    val providerId = "kotlin-js-framework-id"
                    if (model.isFrameworkSelected(providerId)) {
                        model.setFrameworkComponentEnabled(providerId, false)
                    }
                }
            }
        }
    }

    override fun isEnabledForModuleType(moduleType: ModuleType<*>): Boolean = moduleType is JavaModuleType

    override fun canAddSupport(module: Module, facetsProvider: FacetsProvider): Boolean {
        return super.canAddSupport(module, facetsProvider) && module.buildSystemType == BuildSystemType.JPS
    }
}
