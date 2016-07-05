package com.cleaner.config;


import java.util.List;

public final class BaseConstantDef {
    //----------first_page_item 中的配置项的key-------//
    public final static String CONFIG_KEY_MAX_SIZE_OF_GUIDE_VIEW_PAGE = "guideView_maxSizeOfPage";
    public final static String CONFIG_KEY_CLOSE_LISTENING_CHAT = "closeListeningChat"; //是否关闭IM的15分钟监听自动回复功能

    /**
     * 　导航页配置
     */
    public final static String CONFIG_KEY_NAVIGATION_ACTIVITY = "NavigationActivity";
    public final static String CONFIG_KEY_NAVIGATION_ACTIVITY_ITEM_ONLY_MYBILL = "onlyMyBill"; //从九宫格的我的账单直接跳到诊疗卡账单
    public final static String CONFIG_KEY_NAVIGATION_ACTIVITY_ITEM_ONLY_OUTOFHOSPITAL_NAVIGATION = "onlyOutOfHospitalNavigation"; //从九宫格的医院导航直接跳到院外导航
    public final static String CONFIG_KEY_NAVIGATION_ACTIVITY_ITEM_ONLY_RESERVATION_RECORD = "onlyReservationRecord"; //从九宫格的我的预约直接跳到挂号预约记录

    /**
     * 二级目录页是否显示拨打电话按钮的配置
     */
    public final static String CONFIG_KEY_PHONE_CONFIG = "phoneConfig";

    /**
     * 　九宫格首页配置综合服务跳转
     */
    public final static String CONFIG_KEY_SUMMARY_ACTIVITY = "SummaryActivity";
    public final static String CONFIG_KEY_SUMMARY_ACTIVITY_ITEM_HOSDETAIL_ACTIVITY = "HosDetailActivity";
    public final static String CONFIG_KEY_SUMMARY_ACTIVITY_ITEM_NEW_HOSDETAIL_ACTIVITY = "NewHosDetailActivity";
    public final static String CONFIG_KEY_SUMMARY_ACTIVITY_ITEM_HOS_DETAIL_V3_ACTIVITY = "HosDetailV3Activity";

    /**
     * 　综合服务配置：是否包含导航
     */
    public final static String CONFIG_KEY_HOSDETAIL_V3_ACTIVITY = "HosDetailV3Activity";
    public final static String CONFIG_KEY_HOSDETAIL_V3_ACTIVITY_ITEM_NAVIGATION = "Navigation";
    public final static String CONFIG_KEY_HOSDETAIL_V3_ACTIVITY_NONE_HOS_MSG = "none_hos_msg";
    public final static String CONFIG_KEY_HOSDETAIL_V3_ACTIVITY_ITEM_API_V4 = "common_list_v4";

    /**
     * 　收银台页面，用来控制是否显示支付宝支付选项
     */
    public final static String CONFIG_KEY_CASHIER_ACTIVITY = "CashierActivity";
    public final static String CONFIG_KEY_CASHIER_ACTIVITY_ITEM_CARD = "Card";
    public final static String CONFIG_ID_CASHIER_CHANNEL = "86";
    public final static String CONFIG_ID_ADD_REGISTRATION = "87";
    public final static String CASHIER_PPAYMENT_CHANNEL_ALIPAY = "alipay";
    public final static int CASHIER_LAYOUT_CENTER = 0;
    public final static int CASHIER_LAYOUT_BOTH_SIDES = 1;

    /**
     * 预约详情页面
     */
    public final static String CONFIG_KEY_QUERY_SCHEDULE_APPOINTMENT_DETAIL_ACTIVITY = "ScheduleAppointmentDetailActivity";
    public final static String CONFIG_KEY_QUERY_APPOINTMENT_TABACTIVITY_PAY_WITH_CARD = "PayWithCard";
    public final static String CONFIG_KEY_SCHEDULE_APPOINTMENT_DETAIL_ACTIVITY_CANCEL_APPOINTMENT = "cancel_appointment";

    /**
     * 　住院账单配置
     */
    public final static String CONFIG_KEY_QUERY_HOSPITALIZATION_BILL_ACTIVITY_FROM_HOSPITAL_BILL = "QueryHospitalizationBillActivity_HospitalBill"; //住院账单
    public final static String CONFIG_KEY_QUERY_HOSPITALIZATION_BILL_ACTIVITY_ITEM_HOS_FLOW = "FindBillByHospitalizationAndFlowingNoFragment"; //通过住院号和流水号查询
    public final static String CONFIG_KEY_QUERY_HOSPITALIZATION_BILL_ACTIVITY_ITEM_HOS_MOBILE = "FindBillByHospitalizationAndMobileFragment"; //通过住院号查询和手机号
    public final static String CONFIG_KEY_QUERY_HOSPITALIZATION_BILL_ACTIVITY_ITEM_HOS_IDENTIFICATION = "FindBillByHospitalizationAndIdentificationFragment"; //通过住院号查询和身份证号
    public final static String CONFIG_KEY_QUERY_HOSPITALIZATION_BILL_ACTIVITY_ITEM_FLOW = "FindBillByHospitalizationNoFragment"; //通过住院号查询
    public final static String CONFIG_KEY_QUERY_HOSPITALIZATION_BILL_ACTIVITY_ITEM_CARD = "FindBillByVisitingCardFragment"; //通过就诊卡
    public final static String CONFIG_KEY_QUERY_HOSPITALIZATION_BILL_ACTIVITY_QR_CODE = "QR_Code";

    /**
     * 　科室信息页配置
     */
    public final static String CONFIG_KEY_TAB_SEARCH_DOCTOR_ACTIVITY = "TabSearchDoctorActivity";
    public final static String CONFIG_KEY_TAB_SEARCH_DOCTOR_ACTIVITY_ITEM_TITLE_BACK_ICON = "TitleBackIcon";
    public final static String CONFIG_KEY_TAB_SEARCH_DOCTOR_ACTIVITY_ITEM_HOS_DEPARTMENT_FRAGMENT = "HosDepartmentFragment";
    public final static String CONFIG_KEY_TAB_SEARCH_DOCTOR_ACTIVITY_ITEM_HOS_DEPARTMENT_LIST_FRAGMENT = "HosDepartmentListFragment";

    /**
     * 　医生列表配置
     */
    public final static String CONFIG_KEY_DOCTOR_LIST_TAB_ACTIVITY = "DoctorListTabActivity";
    public final static String CONFIG_KEY_DOCTOR_LIST_TAB_ACTIVITY_ITEM_EXPERT = "DepartmentDoctorByExpertFragment";
    public final static String CONFIG_KEY_DOCTOR_LIST_TAB_ACTIVITY_ITEM_NORMAL = "DepartmentDoctorByNormalFragment";
    public final static String CONFIG_KEY_DOCTOR_LIST_TAB_ACTIVITY_ITEM_SPECIAL_NORMAL = "DepartmentDoctorBySpecialNormalFragment";

    /**
     * 　医生列表配置
     */
    public final static String CONFIG_KEY_DOCTOR_LIST_FRAGMENT = "DoctorListFragment";
    public final static String CONFIG_KEY_DOCTOR_LIST_FRAGMENT_ITEM_PROBLEM = "problem";
    public final static String CONFIG_KEY_DOCTOR_LIST_FRAGMENT_ITEM_RESERVATIONSTATE = "ReservationState";

    /**
     * 　医生排班列表配置
     */
    public final static String CONFIG_KEY_DOCTOR_SCHEDULING_FRAGMENT = "DoctorSchedulingFragment";
    public final static String CONFIG_KEY_DOCTOR_SCHEDULING_FRAGMENT_ITEM_GUAHAO = "GuaHao";
    public final static String CONFIG_KEY_DOCTOR_SCHEDULING_FRAGMENT_ITEM_FEE = "Fee";
    public final static String CONFIG_KEY_DOCTOR_SCHEDULING_FRAGMENT_ITEM_COUNT = "Count";
    public final static String CONFIG_KEY_DOCTOR_SCHEDULING_FRAGMENT_ITEM_BUTTON = "Button";
    public final static String CONFIG_KEY_DOCTOR_SCHEDULING_FRAGMENT_ITEM_TIME_SLOT = "TimeSlot";

    /**
     * 　页配置
     */
    public final static String CONFIG_KEY_REGISTRATIONCARD_LIST_ACTIVITY = "RegistrationCardListActivity";
    public final static String CONFIG_KEY_REGISTRATIONCARD_LIST_STYLE = "RegistrationCardListStyle";
    public final static String CONFIG_KEY_REGISTRATIONCARD_LIST_ACTIVITY_ITEM_CARDUSAGE_DESCRIPTION = "CardUsageDescription";
    public final static String CONFIG_KEY_REGISTRATIONCARD_LIST_STYLE_ITEM_ONLY_CARD = "OnlyShowCard";

    /**
     * 　主页面配置
     */
    public final static String CONFIG_KEY_MAIN_ACTIVITY = "MainActivity";
    private final static String CONFIG_KEY_MAIN_ACTIVITY_TAB_HOME_PAGE_SUMMARY_ACTIVITY = "SummaryActivity";
    private final static String CONFIG_KEY_MAIN_ACTIVITY_TAB_HOME_PAGE_RECYCLER_SUMMARY_ACTIVITY = "RecyclerSummaryActivity";
    private final static String CONFIG_KEY_MAIN_ACTIVITY_TAB_HOME_PAGE_RECYCLER_SUMMARY_V2_ACTIVITY = "RecyclerSummaryV2Activity";
    private final static String CONFIG_KEY_MAIN_ACTIVITY_TAB_MESSAGE_TABACTIVITY = "Message_TabActivity";
    private final static String CONFIG_KEY_MAIN_ACTIVITY_TAB_TAB_SEARCH_DOCTOR_ACTIVITY = "TabSearchDoctorActivity";
    private final static String CONFIG_KEY_MAIN_ACTIVITY_TAB_DISCOVERY_ACTIVITY = "DiscoveryActivity";
    private final static String CONFIG_KEY_MAIN_ACTIVITY_TAB_MINE_ACTIVITY = "MineActivity";

    /**
     * 关于页面配置
     */
    public final static String CONFIG_KEY_ABOUNT_ACTIVITY = "AboutActivity";
    public final static String CONFIG_KEY_ABOUNT_ACTIVITY_SHARE = "share";
    //----------first_page_item 中的配置项的key-------//

    /**
     * 　综合服务页配置
     */
    public final static String CONFIG_KEY_HOS_INFO_FRAGMENT = "HosInfoFragment";
    public final static String CONFIG_KEY_HOS_INFO_FRAGMENT_ITEM_LIST_V1 = "ListV1";

    /**
     * 病友圈
     */
    public final static String CONFIG_KEY_SUFFERERS_CIRCLE_ACTIVITY = "SufferersCircleActivity";
    public final static String CONFIG_KEY_SUFFERERS_CIRCLE_ACTIVITY_GUIDE = "guide";

    //----------判断主页时候切换tab用-------//
    public final static int USED_XB_VALUE = 1;
    public final static int USERD_HX_VALUE = 2;
    //----------判断主页时候切换tab用-------//

    public static final String INTENT_PARAM_KEY_H5_JSON = "h5_send_json";
    /**
     * 住院信息
     */
    public final static String CONFIG_KEY_HOSPITALIZATION_INFO_ACTIVITY = "HospitalizationInfoActivity";
    public final static String CONFIG_KEY_HOSPITALIZATION_INFO_ACTIVITY_ITEM_HOSPITAL_BILLS = "Hospital_bills"; //查看住院账单
    public final static String CONFIG_KEY_HOSPITALIZATION_INFO_ACTIVITY_ITEM_HOSPITAL_REPORT = "Hospital_report"; //查看住院报告

    /**
     * 住院账单
     */
    public final static String CONFIG_KEY_HOSPITALIZATION_BILL_ACTIVITY = "HospitalizationBillActivity";
    public final static String CONFIG_KEY_HOSPITALIZATION_BILL_ACTIVITY_ITEM_STREAMLINE = "streamline";
    public final static int DEFALUT_STYLE = 0;
    public final static int STREAMLINE_STYLE = 1;

    /**
     * 药品订单详情页面
     */
    public final static String CONFIG_KEY_DRUG_ORDER_DETAIL_ACTIVITY = "DrugOrderDetailActivity";
    public final static String CONFIG_KEY_DRUG_ORDER_DETAIL_ACTIVITY_ITEM_ONLY_SHOW_ADDRESS = "OnlyShowAddress";

    public final static String CONFIG_DOCTOR_SCHEDULING_TIME_ACTIVITY = "DoctorSchedulingTimeActivity";

    private BaseConstantDef() {
    }

    public static boolean hasSummary(List<String> mActivityConfigs) {
        return mActivityConfigs.contains(CONFIG_KEY_MAIN_ACTIVITY_TAB_HOME_PAGE_SUMMARY_ACTIVITY);
    }

    public static boolean hasRecyclerSummary(List<String> mActivityConfigs) {
        return mActivityConfigs.contains(CONFIG_KEY_MAIN_ACTIVITY_TAB_HOME_PAGE_RECYCLER_SUMMARY_ACTIVITY);
    }

    public static boolean hasRecyclerSummaryV2(List<String> mActivityConfigs) {
        return mActivityConfigs.contains(CONFIG_KEY_MAIN_ACTIVITY_TAB_HOME_PAGE_RECYCLER_SUMMARY_V2_ACTIVITY);
    }

    public static boolean hasMessage(List<String> mActivityConfigs) {
        return mActivityConfigs.contains(CONFIG_KEY_MAIN_ACTIVITY_TAB_MESSAGE_TABACTIVITY);
    }

    public static boolean hasDoctorPage(List<String> mActivityConfigs) {
        return mActivityConfigs.contains(CONFIG_KEY_MAIN_ACTIVITY_TAB_TAB_SEARCH_DOCTOR_ACTIVITY);
    }

    public static boolean hasMinePage(List<String> mActivityConfigs) {
        return mActivityConfigs.contains(CONFIG_KEY_MAIN_ACTIVITY_TAB_MINE_ACTIVITY);
    }

    public static boolean hasDiscoveryPage(List<String> mActivityConfigs) {
        return mActivityConfigs.contains(CONFIG_KEY_MAIN_ACTIVITY_TAB_DISCOVERY_ACTIVITY);
    }
}
