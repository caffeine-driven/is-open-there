package kr.ac.jejunu.model;

import lombok.Data;

/**
 * Created by ghost9087 on 16/06/2017.
 */
@Data
public class ActionResult {
    private Boolean result;

    public ActionResult(Boolean result) {
        this.result = result;
    }
}
