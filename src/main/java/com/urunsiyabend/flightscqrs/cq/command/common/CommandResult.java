package com.urunsiyabend.flightscqrs.cq.command.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommandResult {
    private Boolean isSuccess;
    private String message;

    public static CommandResult success() {
        return new CommandResult(true, null);
    }

    public static CommandResult success(String message) {
        return new CommandResult(true, message);
    }

    public static CommandResult fail(String message) {
        return new CommandResult(false, message);
    }
}