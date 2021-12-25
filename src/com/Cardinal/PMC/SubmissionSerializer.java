package com.Cardinal.PMC;

import java.time.format.DateTimeFormatter;

import com.Cardinal.PMC.Members.Submissions.Submission;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class SubmissionSerializer implements JsonSerializer<Submission> {
	@Override
	public JsonElement serialize(Submission subm, java.lang.reflect.Type type, JsonSerializationContext context) {
		JsonObject root = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJsonTree(subm).getAsJsonObject();
		root.addProperty("description", subm.getDescription().text());
		root.addProperty("author", subm.getAuthor().getName());
		root.addProperty("created", subm.getTimestamp().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		return root;
	}
}
