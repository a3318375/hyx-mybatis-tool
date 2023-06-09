package com.tsm.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.tsm.service.TableInfoService;
import com.tsm.service.impl.TableInfoServiceImpl;
import com.tsm.tool.CacheDataUtils;
import com.tsm.ui.SelectSavePath;
import org.jetbrains.annotations.Nullable;

/**
 * 代码生成菜单
 *
 * @author makejava
 * @version 1.0.0
 * @since 2018/07/17 13:10
 */
public class MainAction extends AnAction {
    /**
     * 构造方法
     *
     * @param text 菜单名称
     */
    MainAction(@Nullable String text) {
        super(text);
    }

    /**
     * 处理方法
     *
     * @param event 事件对象
     */
    @Override
    public void actionPerformed(AnActionEvent event) {
        Project project = event.getProject();
        if (project == null) {
            return;
        }
        // 校验类型映射
        if(!project.getService(TableInfoService.class).typeValidator(CacheDataUtils.getInstance().getSelectDbTable())) {
            // 没通过不打开窗口
            return;
        }
        //开始处理
        new SelectSavePath(event.getProject()).open();
    }
}
