package ru.spb.iac.tests;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.Test;
import static ru.spb.iac.pages.home.Locators.*;
import static ru.spb.iac.pages.Locators.*;
import static ru.spb.iac.pages.registerOfGovernmentService.card.Locators.*;
import static ru.spb.iac.pages.registerOfGovernmentService.PropertySettings.Locators.*;

@TestInstance(Lifecycle.PER_CLASS)
public class RegisterOfGovernmentServiceTest extends TestStarter {

    @Test
    public void create_property(){
        homePage
            .authorisation(approver, approverPassword, true)
            .open(authoritySetings);
        propertySettings
            .go_to_property_add(servicePropertyGroup)
            .createPropertyGroupe("AytotestServiceGroup", true, services, iac, pressCommittee)
            .go_to_property_add(authorytyPropertyGroup)
            .createPropertyGroupe("AytotestAuthorityGroup", true, authority, iac, pressCommittee)
            .go_to_property_add(serviceProperty)
            .createProperty("AutotestServiceProperty", "ServiceProperty", "Hint!", "Список", 3, "AytotestServiceGroup")
            .go_to_property_add(authorityProperty)
            .createProperty("AutotestAuthorityProperty", "AuthorityProperty", "Hint!", "Список",3,"AytotestAuthorityGroup");
        homePage.logOut(logOut);
    }

    @Test
    public void full_agreement_cycle() {

    // Под Согласующим лицом создать полномочие и сервис и направить полномочие в ИОГВ
        homePage
            .authorisation(approver, approverPassword, true);
        creatorNewAuthority
            .create_new_authority(pressCommittee);
        homePage
            .go_to_notifications()
            .go_to_transition_to_authority_through_notifications("Новое полномочие")
            .add_service("Полномочия", authorityName, staticServiceName)
            .clickButton(sendToAgreementButton)
            .fill_out_the_form_to_send_in_iogv("Отправлено в ИОГВ");
        homePage.logOut(logOut);

    // Под Исполнителем заполнить сервис, отправить на согласование
        homePage
            .authorisation(iogv, passwordIogv, false)
            .go_to_notifications()
//            .go_to_transition_to_authority_through_notifications("Необходимо заполнить информацию об услуге/функции")
                .go_to_transition_to_authority_through_notifications("Новое полномочие")
            .open_servise_groups(staticServiceName)
            .open_groups()
            .fill_out_all_the_information_in_the_groups()
            .send_to_approver("Отправлено на согласование");
        homePage.logOut(logOut);

    // Под Согласующим отправить на доработку
        homePage
            .authorisation(approver, approverPassword, true)
            .go_to_notifications()
            .go_to_transition_to_authority_through_notifications("ИОГВ отредактировал сведения об услуге/функции");
        cardCoordinator
            .open_servise_groups(staticServiceName)
            .open_groups()
            .to_approve_group(0)
            .clickButton(sendToAgreementButton)
            .fill_out_the_form_to_send_for_review("Отправлено в ИОГВ", false)
            .clickButton(alertSuccessButton)
            .clickButton(closeModal)
            .set_field_status_in_all_groups(0)
            .clickButton(sendToAgreementButton)
            .fill_out_the_form_to_send_for_review("Отправлено в ИОГВ", true);
        homePage.logOut(logOut);

    // Под Исполнителем согласовать нессогласованные
        homePage
            .authorisation(iogv, passwordIogv, true)
            .go_to_notifications()
            .go_to_transition_to_authority_through_notifications("Необходимо заполнить информацию об услуге/функции");
        cardExecutor
            .open_servise_groups(staticServiceName)
            .open_groups()
            .fill_out_all_the_information_in_the_groups()
            .send_to_approver("Отправлено на согласование");
        homePage.logOut(logOut);

        homePage.authorisation(approver, approverPassword, true)
            .go_to_notifications()
            .go_to_transition_to_authority_through_notifications("ИОГВ отредактировал сведения об услуге/функции");
        cardCoordinator
            .open_servise_groups(staticServiceName)
            .open_groups()
            .set_field_status_in_all_groups(-1)
            .clickButton(saveAuthority);
        homePage.logOut(logOut);

        homePage
            .authorisation(iogv, passwordIogv, false)
            .go_to_notifications()
            .go_to_transition_to_authority_through_notifications("Сведения об услуге/функции согласованы");
        homePage.logOut(logOut);
    }

    @Test
    public void overall_search(){
        homePage
            .authorisation(approver, approverPassword,true)
            .open_registr_of_public_service_data_table()
            .searching_through_overall_serch("Service");
    }

    @Test
    public void advanced_search(){
        homePage
            .authorisation(approver, approverPassword, true)
            .open_registr_of_public_service_data_table()
            .searching_through_advanced_serch("Наименование ИОГВ", pressCommittee);
    }

    @Test
    public void customize_columns() {
        homePage
            .authorisation(approver, approverPassword, true)
            .open_registr_of_public_service_data_table();
        governmentDataTable
            .open_custom_columns("ServiceProperty");
        homePage
            .logOut(logOut);
    }

    @Test
    public void create_municipal_authority(){
        homePage
            .authorisation(approver, approverPassword, true)
            .open_registr_of_public_service_data_table()
            .open_municipal_service_data_table()
            .open_create_authority()
            .create_new_authority(3, "Autotest_Municipal_Authority", false, "Service");
    }

    @Test
    public void test6565(){
        homePage
            .authorisation(approver, approverPassword, true);
//            .open_registr_of_public_service_data_table();
//        helper.waitForElementPresent("#grid-table-government tr");
//        SelenideElement table = $("#grid-table-government");
//        List<SelenideElement> rows = table.findAll("tr");
//        for (SelenideElement row : rows) {
//            List<SelenideElement> cols = row.findAll("td");
//            for (SelenideElement col : cols) {
//                System.out.print(col.getText() + "\t");
//            }
//            System.out.println();
//        }

    }

}
