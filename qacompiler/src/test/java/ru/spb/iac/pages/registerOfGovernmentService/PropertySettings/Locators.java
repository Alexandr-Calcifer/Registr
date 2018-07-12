package ru.spb.iac.pages.registerOfGovernmentService.PropertySettings;

public class Locators {

    public static String head = ".center.authority-settings";
    static String propertyAddButton = ".btn.btn-success.dropdown-toggle";
    public static String serviceProperty = "[href='/authority/settings/new_property/7/all']";
    public static String servicePropertyGroup = "[href='/authority/settings/new_group/7/all']";
    public static String authorytyPropertyGroup = "[href='/authority/settings/new_group/6/all']";
    public static String authorityProperty = "[href='/authority/settings/new_property/6/all']";
    public static String services = "[href='#services']";
    public static String authority = "[href='#authority']";
    public static String all = "[href='#all']";
    static String serviceGroups = "#services-accordion .col-md-11.pull-left.no-padding-left.name-group";
    static String servicePropertyInGroupTab = "#services-accordion .panel-collapse.collapse.in .col-md-11.pull-left.no-padding-left";
    static String authorityGroups = "#authority-accordion .col-md-11.pull-left.no-padding-left.name-group";
    static String authorityPropertyInGroupTab = "#authority-accordion .panel-collapse.collapse.in .col-md-11.pull-left.no-padding-left";
    static String allServices = "#reorder-all-list .col-md-12.property-name-in-list";
    static String searchQueryInAllServices = "#all [style='display: block;'] .col-md-12.property-name-in-list";
    static String filter = ".ace-icon.fa.fa-chevron-down";
    static String filterPropertyName = "#propertyName";
    static String applyFilter = "#applyFilter";

    static String type = "#property_type";
    static String name = "#property_name";
    static String shortName = "#property_short_name";
    static String titleHint = "#popup_title";
    static String multiple = "#main-settings div:nth-child(9) div label";
    static String groupParticipatesCycle = "#main-settings div:nth-child(4) div label";
    static String showMeasuce = "#show_measure div label";
    static String showWysiwyg = "#setWysiwyg div label";
    static String valueList = "#type_values_row .form-group:not(.no-margin-bottom) .form-inline.col-md-11.no-padding.propertyValuesForm";
    static String defaultValue = ".form-inline.col-md-11.no-padding.propertyValuesForm .pull-left.inline";
    static String refillList = "#property_refill_list div label";
    static String valueName = "[name='property_type_values[]']";
    static String typeValuesCode = "[name='property_type_values_code[]']";
    static String deletePropertyValuesForm = "#deletePropertyValuesForm";
    static String addPropertyValuesForm = "#addPropertyValuesForm";
    static String classifierTree = "#tree_classifier div label";
    static String classifier = "#id_classifier";
    static String elementsTable = "#id_property_linked_table";
    static String groupName = "#name_new_group";
    static String propertyGroup = "#property_group";
    static String propertySortGroup = "#property_sortGroup";
    static String propertySortGroupList = "#property_sortGroup option";
    static String participationOfTheGroupInTheFullCycle = "#main-settings div:nth-child(4) div label";
    static String save = "#save";
    static String searchIOGV = "#search-iogv";
    static String accessSetting = "[href='#access-settings']";
    static String readAccess = ".col-md-12.settings-container .panel.panel-default[style^='display: block;'] .pull-left.inline.pr-47.pt-4 .lbl.middle";

}
