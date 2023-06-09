package com.tsm.tool;

import com.tsm.config.Settings;
import com.tsm.entity.TableInfo;
import com.tsm.entity.Template;
import com.tsm.service.impl.CodeGenerateServiceImpl;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * 额外的代码生成工具
 *
 * @author makejava
 * @version 1.0.0
 * @since 2018/11/01 10:11
 */
public class ExtraCodeGenerateUtils {
    /**
     * 代码生成服务
     */
    private CodeGenerateServiceImpl codeGenerateService;
    /**
     * 表信息对象
     */
    private TableInfo tableInfo;
    /**
     * 文件覆盖提示
     */
    private Boolean title;

    /**
     * 设置实例对象
     */
    private Settings settings = Settings.getInstance();


    public ExtraCodeGenerateUtils(CodeGenerateServiceImpl codeGenerateService, TableInfo tableInfo, Boolean title) {
        this.codeGenerateService = codeGenerateService;
        this.tableInfo = tableInfo;
        this.title = title;
    }

    /**
     * 生成代码
     *
     * @param templateName 模板名称
     * @param param        附加参数
     */
    public void run(String templateName, Map<String, Object> param) {
        // 获取到模板
        Template currTemplate = null;
        for (Template template : settings.getTemplateGroupMap().get(settings.getCurrTemplateGroupName()).getElementList()) {
            if (Objects.equals(template.getName(), templateName)) {
                currTemplate = template;
            }
        }
        if (currTemplate == null) {
            return;
        }
        // 生成代码
        codeGenerateService.generate(Collections.singletonList(currTemplate), Collections.singletonList(this.tableInfo), this.title, param);
    }
}
