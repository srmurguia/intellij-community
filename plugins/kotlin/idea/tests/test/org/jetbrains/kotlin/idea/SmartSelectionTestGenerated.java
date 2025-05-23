// Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

package org.jetbrains.kotlin.idea;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.idea.base.plugin.KotlinPluginMode;
import org.jetbrains.kotlin.idea.base.test.TestRoot;
import org.jetbrains.kotlin.idea.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.idea.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.runner.RunWith;

/**
 * This class is generated by {@link org.jetbrains.kotlin.testGenerator.generator.TestGenerator}.
 * DO NOT MODIFY MANUALLY.
 */
@SuppressWarnings("all")
@TestRoot("idea/tests")
@TestDataPath("$CONTENT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
@TestMetadata("testData/smartSelection")
public class SmartSelectionTestGenerated extends AbstractSmartSelectionTest {
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public final KotlinPluginMode getPluginMode() {
        return KotlinPluginMode.K1;
    }

    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest(this::doTestSmartSelection, this, testDataFilePath);
    }

    @TestMetadata("afterRightParenthesis.kt")
    public void testAfterRightParenthesis() throws Exception {
        runTest("testData/smartSelection/afterRightParenthesis.kt");
    }

    @TestMetadata("afterRightParenthesis2.kt")
    public void testAfterRightParenthesis2() throws Exception {
        runTest("testData/smartSelection/afterRightParenthesis2.kt");
    }

    @TestMetadata("afterRightParenthesis3.kt")
    public void testAfterRightParenthesis3() throws Exception {
        runTest("testData/smartSelection/afterRightParenthesis3.kt");
    }

    @TestMetadata("beforeComma.kt")
    public void testBeforeComma() throws Exception {
        runTest("testData/smartSelection/beforeComma.kt");
    }

    @TestMetadata("beforeComment.kt")
    public void testBeforeComment() throws Exception {
        runTest("testData/smartSelection/beforeComment.kt");
    }

    @TestMetadata("beforeDot.kt")
    public void testBeforeDot() throws Exception {
        runTest("testData/smartSelection/beforeDot.kt");
    }

    @TestMetadata("beforeKDocComment.kt")
    public void testBeforeKDocComment() throws Exception {
        runTest("testData/smartSelection/beforeKDocComment.kt");
    }

    @TestMetadata("beforeRightParenthesis.kt")
    public void testBeforeRightParenthesis() throws Exception {
        runTest("testData/smartSelection/beforeRightParenthesis.kt");
    }

    @TestMetadata("beforeRightParenthesis2.kt")
    public void testBeforeRightParenthesis2() throws Exception {
        runTest("testData/smartSelection/beforeRightParenthesis2.kt");
    }

    @TestMetadata("beforeRightParenthesis3.kt")
    public void testBeforeRightParenthesis3() throws Exception {
        runTest("testData/smartSelection/beforeRightParenthesis3.kt");
    }

    @TestMetadata("commentsAndExtraSpaces.kt")
    public void testCommentsAndExtraSpaces() throws Exception {
        runTest("testData/smartSelection/commentsAndExtraSpaces.kt");
    }

    @TestMetadata("labelledStatement.kt")
    public void testLabelledStatement() throws Exception {
        runTest("testData/smartSelection/labelledStatement.kt");
    }

    @TestMetadata("labelledThis.kt")
    public void testLabelledThis() throws Exception {
        runTest("testData/smartSelection/labelledThis.kt");
    }

    @TestMetadata("lambdaCalls.kt")
    public void testLambdaCalls() throws Exception {
        runTest("testData/smartSelection/lambdaCalls.kt");
    }

    @TestMetadata("multilineCalls.kt")
    public void testMultilineCalls() throws Exception {
        runTest("testData/smartSelection/multilineCalls.kt");
    }

    @TestMetadata("multilineOperations.kt")
    public void testMultilineOperations() throws Exception {
        runTest("testData/smartSelection/multilineOperations.kt");
    }

    @TestMetadata("objectLiteral.kt")
    public void testObjectLiteral() throws Exception {
        runTest("testData/smartSelection/objectLiteral.kt");
    }

    @TestMetadata("parenthesized.kt")
    public void testParenthesized() throws Exception {
        runTest("testData/smartSelection/parenthesized.kt");
    }

    @TestMetadata("simple.kt")
    public void testSimple() throws Exception {
        runTest("testData/smartSelection/simple.kt");
    }

    @TestMetadata("superExpression.kt")
    public void testSuperExpression() throws Exception {
        runTest("testData/smartSelection/superExpression.kt");
    }

    @TestMetadata("superExpressionWithLabel.kt")
    public void testSuperExpressionWithLabel() throws Exception {
        runTest("testData/smartSelection/superExpressionWithLabel.kt");
    }

    @TestMetadata("superExpressionWithLabelAndType.kt")
    public void testSuperExpressionWithLabelAndType() throws Exception {
        runTest("testData/smartSelection/superExpressionWithLabelAndType.kt");
    }

    @TestMetadata("superExpressionWithType.kt")
    public void testSuperExpressionWithType() throws Exception {
        runTest("testData/smartSelection/superExpressionWithType.kt");
    }

    @TestMetadata("typeInSuperExpression.kt")
    public void testTypeInSuperExpression() throws Exception {
        runTest("testData/smartSelection/typeInSuperExpression.kt");
    }

    @TestMetadata("typeInSuperExpressionWithLabel.kt")
    public void testTypeInSuperExpressionWithLabel() throws Exception {
        runTest("testData/smartSelection/typeInSuperExpressionWithLabel.kt");
    }
}
