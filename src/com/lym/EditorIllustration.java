package com.lym;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.ComponentPopupBuilder;
import com.intellij.openapi.ui.popup.JBPopup;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.util.TextRange;
import com.lym.service.TranslateService;

import javax.swing.*;

public class EditorIllustration extends AnAction {


    @Override
    public void update(AnActionEvent e) {
        final Project project = e.getProject();
        final Editor editor = e.getData(CommonDataKeys.EDITOR);
        e.getPresentation().setVisible(project != null && editor != null&&
                editor.getSelectionModel().hasSelection());
    }

    @Override
    public void actionPerformed(final AnActionEvent e) {
        //Get all the required data from data keys
        final Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
        final Project project = e.getProject();
        //Access document, caret, and selection
        final Document document = editor.getDocument();
        final SelectionModel selectionModel = editor.getSelectionModel();
        final int start = selectionModel.getSelectionStart();
        final int end = selectionModel.getSelectionEnd();
        //Making the replacement
        WriteCommandAction.runWriteCommandAction(project, () -> {
            String text = document.getText(TextRange.create(start, end));
            if (text.isEmpty()) {
                return ;
            }
            String translate;
            try {
                translate = TranslateService.translate(text);
            } catch (Exception ex) {
                translate = "老大，在下找不到答案";
                ex.printStackTrace();
            }
            JLabel jLabel = new JLabel(translate);
            ComponentPopupBuilder componentPopupBuilder = JBPopupFactory.getInstance().createComponentPopupBuilder(jLabel, jLabel);
            JBPopup popup = componentPopupBuilder.createPopup();
            popup.showInBestPositionFor(editor);
            }
        );
        selectionModel.removeSelection();
    }


}