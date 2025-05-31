package tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.step;

public class PracticeFormTest extends TestBase {

    @Test
    @Tag("demoqa")
    void fillFormTest() {

        step("Open registration form", () -> {
            open("/automation-practice-form");
            executeJavaScript("$('#fixedban').remove()");
            executeJavaScript("$('footer').remove()");
        });

        step("Fill registration form", () -> {
            $("#firstName").setValue("Raccoon");
            $("#lastName").setValue("Qa");
            $("#userEmail").setValue("test@test.com");
            $("#genterWrapper").$(byText("Female")).click();
            $("#userNumber").setValue("1234567890");
            $("#dateOfBirthInput").click();
            $(".react-datepicker").shouldBe(visible);
            $(".react-datepicker__month-select").selectOption("May");
            $(".react-datepicker__year-select").selectOption("2000");
            $(".react-datepicker__day--010").click();
            $("#subjectsInput").setValue("Maths").pressEnter();
            $("#hobbiesWrapper").$(byText("Sports")).click();
            $("#hobbiesWrapper").$(byText("Music")).click();
            $("#uploadPicture").uploadFromClasspath("mem_1.jpg");
            $("#currentAddress").setValue("Current Address");
            $("#react-select-3-input").setValue("NCR").pressEnter();
            $("#react-select-4-input").setValue("Delhi").pressEnter();
            $("#submit").click();
        });

        step("Check result form", () -> {
            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
            $x("//td[text()='Student Name']").parent().shouldHave(text("Raccoon Qa"));
            $x("//td[text()='Student Email']").parent().shouldHave(text("test@test.com"));
            $x("//td[text()='Gender']").parent().shouldHave(text("Female"));
            $x("//td[text()='Mobile']").parent().shouldHave(text("1234567890"));
            $x("//td[text()='Date of Birth']").parent().shouldHave(text("10 May,2000"));
            $x("//td[text()='Subjects']").parent().shouldHave(text("Maths"));
            $x("//td[text()='Subjects']").parent().shouldHave(text("Maths"));
            $x("//td[text()='Hobbies']").parent().shouldHave(text("Sports, Music"));
            $x("//td[text()='Picture']").parent().shouldHave(text("mem_1.jpg"));
            $x("//td[text()='Address']").parent().shouldHave(text("Current Address"));
            $x("//td[text()='State and City']").parent().shouldHave(text("NCR Delhi"));
        });
    }
}
