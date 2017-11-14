package ru.stqa.pft.rest;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import org.testng.SkipException;

public class TestBase {

    public void skipIfNotFixed(int issueId) {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    private boolean isIssueOpen(int issueId) {
        String json = RestAssured.get("http://demo.bugify.com/api/issues.json", issueId).asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        if (issues.getAsJsonArray().get(0).getAsJsonObject().get("state_name").toString().contains("Resolved")) {
            return false;
        } else {
            return true;
        }
    }
}
