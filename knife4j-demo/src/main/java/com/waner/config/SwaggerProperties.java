package com.waner.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ale on 2022/7/2
 */
@Data
@ConfigurationProperties(prefix = "pd.swagger")
public class SwaggerProperties {

    /**
     * 标题
     */
    private String title = "在线文档";

    /**
     * 自定义组名
     */
    private String group = "";

    /**
     * 描述
     */
    private String description = "在线文档";

    /**
     * 版本
     */
    private String version = "1.0";

    /**
     * 联系人
     */
    private Contact contact = new Contact();

    /**
     * swagger会解析的包路径
     */
    private String basePackage = "com.waner";

    /**
     * swagger会解析的url规则
     */
    private List<String> basePath = new ArrayList<>();

    /**
     * 在basePath基础上需要排除的url规则
     */
    private List<String> excludePath = new ArrayList<>();


    /**
     * 分组文档
     */
    private Map<String, DocketInfo> docket = new LinkedHashMap<>();


    public String getGroup() {
        if (group == null || "".equals(group)) {
            return title;
        }
        return group;
    }

    @Data
    public static class DocketInfo {
        /**
         * 标题
         */
        private String title = "在线文档";

        /**
         * 自定义组名
         */
        private String group = "";

        /**
         * 描述
         */
        private String description = "在线文档";

        /**
         * 版本
         */
        private String version = "1.0";

        /**
         * 联系人
         */
        private Contact contact = new Contact();

        /**
         * swagger会解析的包路径
         */
        private String basePackage = "com.waner";

        /**
         * swagger会解析的url规则
         */
        private List<String> basePath = new ArrayList<>();

        /**
         * 在basePath基础上需要排除的url规则
         */
        private List<String> excludePath = new ArrayList<>();

        public String getGroup() {
            if (group == null || "".equals(group)) {
                return title;
            }
            return group;
        }
    }


    @Data
    public static class Contact {
        /**
         * 联系人名称
         */
        private String name = "waner";


        /**
         * 联系人url
         */
        private String url = "";


        /**
         * 联系人邮箱
         */
        private String email;


    }
}
