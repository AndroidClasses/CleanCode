package com.cleaner.config;

import java.io.Serializable;

/*
            celltype:cell展示形式 LA(名字＋箭头) ILA(icon＋名字＋箭头) ILL（icon+名字＋简单介绍(sub_title)）Header (显示在页面最上方，高度为79dp) 空(名字)
            defaulticon:默认icon，需要是客户端存在的资源
            funcionid:必填，不可重复。
            hiden:是否隐藏，0不隐藏，1隐藏
            iconurl:头部icon资源url
            needLogin:是否需要登录，0（不需要） 1（需要）
            status:cell状态，0（正常状态）1（灰色，禁止进入下一级）
            “sub_title”:简单介绍（在ILL有效，在cell尾部文字）
            title:标题
            type:后续数据类型 t（树形结构）m（webview） n（native）
            webURL：url（m类型有效）
 */
public class FirstPageItemInfo implements Serializable {
    private static final long serialVersionUID = 4259101151277007424L;

//    private int hiden;
//    private String sub_title;
//    private String type;
//    private String iconurl;

    private String defaulticon; //首页item图标

    private String title; //首页 item标题
    private String hint; //首页 item标题下方提示
    private boolean item_reuse_fix_title; // 打开item后，重用item标题
    private String funcionid;       //首页item类型

//    private CategoryConfig category_config;

    private boolean status;

    private boolean needLogin;
//    private String item_category_page;

    private String webURL;

    //    private String item_target_dev_url;
    //url 是否需要传入参数
    private boolean item_url_need_paras;
    private String iconurl;
    private int type;  //11.客户端function,12.外部url

    public void setWebURL(String webURL) {
        this.webURL = webURL;
    }

//    public String getItem_category_page() {

    //    }
//        this.item_category_page = item_category_page;
//    public void setItem_category_page(String item_category_page) {
//
//    }
//        return item_category_page;

    public boolean isNeedLogin() {
        return needLogin;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setNeedLogin(boolean needLogin) {
        this.needLogin = needLogin;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getFuncionid() {
        return funcionid;
    }


    public void setFuncionid(String funcionid) {
        this.funcionid = funcionid;
    }


    public String getDefaulticon() {
        return defaulticon;
    }

    public String getDisableicon() {
        return defaulticon + "_disable";
    }

    public void setDefaulticon(String defaulticon) {
        this.defaulticon = defaulticon;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

//    public String getItem_target_dev_url() {
//        return item_target_dev_url;
//    }
//
//    public void setItem_target_dev_url(String item_target_dev_url) {
//        this.item_target_dev_url = item_target_dev_url;
//    }

//    public void setCategory_config(CategoryConfig config){
//        category_config = config;
//    }
//
//    public CategoryConfig getCategory_config(){
//        return category_config;
//    }

    /**
     * @return the item_reuse_fix_title
     */
    public boolean isItem_reuse_fix_title() {
        return item_reuse_fix_title;
    }

    /**
     * @param itemReuseFixTitle the item_reuse_fix_title to set
     */
    public void setItem_reuse_fix_title(boolean itemReuseFixTitle) {
        this.item_reuse_fix_title = itemReuseFixTitle;
    }

    /**
     * @return the item_url_need_paras
     */
    public boolean isItem_url_need_paras() {
        return item_url_need_paras;
    }

    /**
     * @param itemUrlNeedParas the item_url_need_paras to set
     */
    public void setItem_url_need_paras(boolean itemUrlNeedParas) {
        this.item_url_need_paras = itemUrlNeedParas;
    }

    public String getIconurl() {
        return iconurl;
    }

    public void setIconurl(String iconurl) {
        this.iconurl = iconurl;
    }


    // depress extract dependencies.
//    public String getWebURL() {
////        return webURL;
//        return SystemFunction.getRuntimeNavigationUrl(webURL);
//    }
//
//    public static final String getTitle(Context context, String title) {
//        return BaseFindIDUtils.getFirstPageStringByName(context, title);
//    }
//
//    public static final String getHint(Context context, String hint) {
//        return BaseFindIDUtils.getFirstPageStringByName(context, hint);
//    }
}
