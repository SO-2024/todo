package com.todolist.todo.model;

import java.util.HashMap;
import java.util.Map;

public class InputErrors {
	private Map<String, String> Errors = new HashMap<String, String>();

	public InputErrors(String Component, String Detail) {
		addError(Component, Detail);
	}

	public void addError(String Component, String Detail) {
		Errors.put(Component, Detail);
	}

	public String getErrorDetail(String Component) {
		return this.Errors.get(Component);
	}

	public Map<String, String> getAllErrors() {
		return this.Errors;
	}
}