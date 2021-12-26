package com.Cardinal.PMC;

import com.Cardinal.PMC.Members.Submissions.Submission;
import com.Cardinal.PMC.lang.UnloadedResourceExcpetion;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class SubmissionSerializer implements JsonSerializer<Submission> {
	@Override
	public JsonElement serialize(Submission subm, java.lang.reflect.Type type, JsonSerializationContext context) {
		JsonObject root = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJsonTree(subm).getAsJsonObject();
		try {
			root.addProperty("description", subm.getDescription().text());
			root.addProperty("author", subm.getAuthor().getName());
		} catch (UnloadedResourceExcpetion exc) { }
		return root;
	}
}
