package com.Cardinal.PMC;

import java.io.IOException;

import com.Cardinal.PMC.Forums.ThreadLoader;
import com.Cardinal.PMC.Members.MemberManager;
import com.Cardinal.PMC.Members.Submissions.Submission;
import com.Cardinal.PMC.Members.Submissions.SubmissionLoader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * A class used to represent
 * <a href='https://www.planetminecraft.com/'>PlanetMinecraft</a>.
 * 
 * @author Cardinal System
 *
 */
public class PlanetMinecraft {

	public static void main(String[] args) throws IOException {
		PlanetMinecraft pmc = new PlanetMinecraft();
		Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(Submission.class, new SubmissionSerializer()).create();
		if (args.length == 6 && args[0].equals("searchSubmissions")) {
			for (Submission subm : pmc.getSubmissions().searchSubmissionsFeedPages(args[1], Submission.Type.valueOf(args[2]), Submission.Feed.valueOf(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5])))
				System.out.println(gson.toJson(pmc.getSubmissions().load(subm)));
		} else if (args.length == 5 && args[0].equals("getFeedTypePages")) {
			for (Submission subm : pmc.getSubmissions().getFeedTypePages(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Submission.Type.valueOf(args[3]), Submission.Feed.valueOf(args[4])))
				System.out.println(gson.toJson(pmc.getSubmissions().load(subm)));
		} else System.err.println("No command executed");
	}

	static {
		System.err.println("--- PMC-API ---\n(https://github.com/TheCardinalSystem/PMC-API/)\nAuthor: Cardinal System\nVersion: 0.0.1 BETA\nCredits: Powered by JSoup (https://jsoup.org/)\n--- PMC-API ---");
	}

	private ThreadLoader threadLoader;
	private SubmissionLoader subLoader;
	private MemberManager memberManager;

	/**
	 * Constructs a new {@link PlanetMinecraft}.
	 */
	public PlanetMinecraft() {
		//this.threadLoader = new ThreadLoader();
		this.subLoader = new SubmissionLoader();
		//this.memberManager = new MemberManager();
	}

	/**
	 * Gets the forum manager for this PMC instance.
	 * 
	 * @return the forum manager.
	 */
	public ThreadLoader getForums() {
		return threadLoader;
	}

	/**
	 * Gets the submissions manager for this PMC instance.
	 * 
	 * @return the submissions manager.
	 */
	public SubmissionLoader getSubmissions() {
		return subLoader;
	}

	/**
	 * Gets the user manager for this PMC instance.
	 * 
	 * @return the user manager.
	 */
	public MemberManager getMemberManager() {
		return memberManager;
	}

}
