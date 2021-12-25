package com.Cardinal.PMC;

import java.io.IOException;
import java.util.ArrayList;

import com.Cardinal.PMC.Forums.ThreadLoader;
import com.Cardinal.PMC.Members.MemberManager;
import com.Cardinal.PMC.Members.Submissions.Submission;
import com.Cardinal.PMC.Members.Submissions.SubmissionLoader;
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
		if (args.length >= 4 && args[0].startsWith("searchSubmissions")) {
			PlanetMinecraft pmc = new PlanetMinecraft();
			var submissions = new ArrayList<Submission>();
			if (args[0].endsWith("Feed"))
				for (Submission subm : pmc.getSubmissions().searchSubmissionsFeed(args[1], Submission.Type.valueOf(args[2]), Submission.Feed.valueOf(args[3]), Integer.parseInt(args[4])))
					submissions.add(pmc.getSubmissions().load(subm));
			else for (Submission subm : pmc.getSubmissions().searchSubmissions(args[1], Submission.Type.valueOf(args[2]), Integer.parseInt(args[3])))
					 submissions.add(pmc.getSubmissions().load(subm));
			System.out.println(new GsonBuilder().registerTypeHierarchyAdapter(Submission.class, new SubmissionSerializer()).create().toJson(submissions));
		}
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
