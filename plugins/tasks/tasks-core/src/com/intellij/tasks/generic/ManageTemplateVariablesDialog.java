// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.tasks.generic;

import com.intellij.execution.util.ListTableWithButtons;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.tasks.TaskBundle;
import com.intellij.util.ui.AbstractTableCellEditor;
import com.intellij.util.ui.ColumnInfo;
import com.intellij.util.ui.ListTableModel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.List;

public class ManageTemplateVariablesDialog extends DialogWrapper {
  private final TemplateVariablesTable myTemplateVariableTable;

  protected ManageTemplateVariablesDialog(final @NotNull Component parent) {
    super(parent, true);
    myTemplateVariableTable = new TemplateVariablesTable();
    setTitle(TaskBundle.message("dialog.title.template.variables"));
    init();
  }

  public void setTemplateVariables(List<TemplateVariable> list) {
    myTemplateVariableTable.setValues(list);
  }

  public List<TemplateVariable> getTemplateVariables() {
    return myTemplateVariableTable.getTemplateVariables();
  }

  @Override
  protected @Nullable JComponent createCenterPanel() {
    return myTemplateVariableTable.getComponent();
  }

  private static class TemplateVariablesTable extends ListTableWithButtons<TemplateVariable> {
    TemplateVariablesTable() {
      getTableView().getEmptyText().setText(TaskBundle.message("status.text.no.variables"));
    }

    @Override
    protected ListTableModel<TemplateVariable> createListModel() {
      final ColumnInfo<TemplateVariable, @NlsContexts.ListItem String> name = new ElementsColumnInfoBase<>(TaskBundle.message("column.name.name")) {
        @Override
        protected @NotNull String getDescription(final TemplateVariable templateVariable) {
          return templateVariable.getDescription();
        }

        @Override
        public @NotNull String valueOf(final TemplateVariable templateVariable) {
          return templateVariable.getName();
        }

        @Override
        public boolean isCellEditable(TemplateVariable templateVariable) {
          return !templateVariable.isReadOnly();
        }

        @Override
        public void setValue(TemplateVariable templateVariable, String s) {
          if (s.equals(valueOf(templateVariable))) {
            return;
          }
          templateVariable.setName(s);
          setModified();
        }
      };

      final ColumnInfo<TemplateVariable, @NlsContexts.ListItem String> value = 
        new ElementsColumnInfoBase<>(TaskBundle.message("column.name.value")) {
        @Override
        public @NotNull String valueOf(TemplateVariable templateVariable) {
          return templateVariable.getValue();
        }

        @Override
        public boolean isCellEditable(TemplateVariable templateVariable) {
          return !templateVariable.isReadOnly();
        }

        @Override
        public void setValue(TemplateVariable templateVariable, String s) {
          templateVariable.setValue(s);
          setModified();
        }

        @Override
        public TableCellRenderer getRenderer(TemplateVariable variable) {
          if (variable.isHidden()) {
            return (table, value1, isSelected, hasFocus, row, column) -> {
              return new JPasswordField(value1.toString()); //NON-NLS
            };
          }
          return super.getRenderer(variable);
        }

        @Override
        public @Nullable TableCellEditor getEditor(final TemplateVariable variable) {
          if (variable.isHidden()) {
            return new AbstractTableCellEditor() {
              private JPasswordField myPasswordField;
              @Override
              public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                myPasswordField = new JPasswordField(variable.getValue());
                return myPasswordField;
              }

              @Override
              public Object getCellEditorValue() {
                return myPasswordField.getText(); //NON-NLS
              }
            };
          }
          return super.getEditor(variable);
        }

        @Override
        protected @NotNull String getDescription(TemplateVariable templateVariable) {
          return templateVariable.getDescription();
        }
      };

      final ColumnInfo<TemplateVariable, Boolean> isShownOnFirstTab = new ColumnInfo<>(TaskBundle.message("column.name.show.on.first.tab")) {
        @Override
        public @NotNull Boolean valueOf(TemplateVariable o) {
          return o.isShownOnFirstTab();
        }

        @Override
        public void setValue(TemplateVariable variable, Boolean value) {
          variable.setShownOnFirstTab(value);
          setModified();
        }

        @Override
        public Class<?> getColumnClass() {
          return Boolean.class;
        }

        @Override
        public boolean isCellEditable(TemplateVariable variable) {
          return !variable.isReadOnly();
        }

        @Override
        public @NotNull String getTooltipText() {
          return TaskBundle.message("tooltip.whether.this.template.variable.will.be.shown.in.general.tab");
        }
      };

      final ColumnInfo<TemplateVariable, Boolean> isHidden = new ColumnInfo<>(TaskBundle.message("column.name.hide")) {
        @Override
        public @NotNull Boolean valueOf(TemplateVariable o) {
          return o.isHidden();
        }

        @Override
        public void setValue(TemplateVariable variable, Boolean value) {
          variable.setHidden(value);
          setModified();
          // value column editor may be changed
          TemplateVariablesTable.this.refreshValues();
        }

        @Override
        public Class<?> getColumnClass() {
          return Boolean.class;
        }

        @Override
        public boolean isCellEditable(TemplateVariable variable) {
          return !variable.isReadOnly();
        }

        @Override
        public @NotNull String getTooltipText() {
          return TaskBundle.message("tooltip.whether.this.template.variable.will.be.hidden.like.password.field");
        }
      };
      return new ListTableModel<>(name, value, isShownOnFirstTab, isHidden);
    }

    @Override
    protected TemplateVariable createElement() {
      return new TemplateVariable("", "");
    }

    @Override
    protected boolean isEmpty(TemplateVariable element) {
      return StringUtil.isEmpty(element.getName()) && StringUtil.isEmpty(element.getValue());
    }

    @Override
    protected TemplateVariable cloneElement(final TemplateVariable variable) {
      return variable.clone();
    }

    @Override
    protected boolean canDeleteElement(final TemplateVariable selection) {
      return true;
    }

    public List<TemplateVariable> getTemplateVariables() {
      return getElements();
    }
  }
}
