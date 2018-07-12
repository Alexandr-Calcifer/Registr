package ru.spb.iac.pages.registerOfGovernmentService.card;

public class Locators {

    public static String sendToAgreementButton = "#send_btn";
    protected static String inputNewDate = "#newdate";
    protected static String comment = ".form-control[name='newComment']";
    protected static String sendControl = "#sendcontrol";
    protected static String addNewServiceButton = "#btn_add_service";
    protected static String setServiceName = "#new_service";
    protected static String saveServiceButton = "#btn_save_service";
    protected static String serviceTypeService = ".//*[@id='service-type-block']/label[1]";
    protected static String serviceTypeFunction = ".//*[@id='service-type-block']/label[2]";
    protected static String serviceTypeFunctionMonitorAndControl = ".//*[@id='service-type-block']/label[3]";
    protected static String modalWindowAddInPlan = ".text-primary";
    protected static String notAddInPlan = ".btn.btn-sm[data-bb-handler='cancel']";
    protected static String addInPlan = ".btn.btn-primary.btn-sm";
    protected static String getServicesList = ".collapsed.no_link";
    protected static String periodOfExicution = ".//*[@id='addScheduleForm']//input[@value='']";
    protected static String patternNumberServise = "\\d{4}";
    protected static String addNewSchedule = "#addNewShedule";
    protected static String action = ".btn.dropdown-toggle";
    protected static String modalDialog = ".modal-dialog";
    protected static String listOfVisibleServiceGroups = ".panel-collapse.collapse.in .row.odd.padding-TB-10";
    protected static String serviceAllBack = "//*[@id=\"service_all_back\"]";
    protected static String currentVisibleGroup = "#service_group [class=''] .row.padding-tb-10[data-bad='1']";
    protected static String editVisibleButton = "#service_group [class=''] .row.padding-tb-10[data-bad='1'] #btn_object_group-edit";
    protected static String notDisabledDateField = ".//div[@class='']/div[@class='row padding-tb-10']//input[not(@disabled=\"\") and not(@disabled=\"disabled\")]";
    protected static String formControl = "#form_service #service_group .row.padding-tb-10[data-bad='1'] .form-control:not(:disabled)";
    protected static String btnObjectGroupSave = ".//*[@id='btn_object_group_save' and @class='col-md-11 col-md-offset-1']";
    protected static String btnSave = "#btn_object_group_save:not(.hide) #btn_object_group-save";
    protected static String boxServiceGroup = "#box_service_group";
    protected static String boxServiceGroupListValues  = "#box_service_group option";
    protected static String fileEditor = ".files.file-helper.file_editor:not(:disabled)";
    protected static String linkEditor = ".col-md-12.input-sm.link_editor:not(:disabled)";
    protected static String visibleIcons = ".//div[@id=\"service_group\"]//div[@class=\"\"]//span[@class=\"label label-lg label-danger arrowed-right\"]";
    protected static String toApproveAllInformationsGroupButton = "[class=''] [class=''] #access_btn";
    protected static String buttonSuccessNotActive = "#service_group [class=''] .row.padding-tb-10[data-bad='1'] .btn.btn-white.fix-height-34.btn-success.btn-successfix";
    protected static String buttonNotViewedActive = "#service_group [class=''] .row.padding-tb-10[data-bad='1'] .btn.btn-white.fix-height-34.btn-whitefix.active";
    protected static String buttonNotSuccessNotActive = "#service_group [class=''] .row.padding-tb-10[data-bad='1'] .btn.btn-white.fix-height-34.btn-danger.btn-dangerfix";
    protected static String buttonSuccessChecked = "#service_group [class=''] .row.padding-tb-10[data-bad='1'] .btn-group .btn-group :checked";
    protected static String alertServicesIsNotReviewed = ".//div[@class='bootbox-body']/div[@class='alert alert-warning']";
    public static String alertSuccessButton = ".btn.btn-success.no-border";
    public static String closeModal = "#controlModal .close";
    protected static String alertWindow = ".alert.alert-warning[role='alert']";
    protected static String alertTextMessegeInExecuteWindow = "На форме есть поля со статусом: \"Не просмотрено\"";
    public static String saveAuthority = ".col-xs-12.margin-top-mg .btn.btn-success.pull-right";
    protected static String buttonGoBackToServiceGroups = "#service_all_back";
}
