package com.Cardinal.PMC.Members.Submissions;

import java.io.IOException;

import com.Cardinal.PMC.lang.UnloadedResourceExcpetion;
import com.google.gson.annotations.Expose;

/**
 * A class used to represent downloadable submissions (e.i. {@linkplain Mod},
 * {@linkplain Project}, {@linkplain Skin}).
 * 
 * @author Cardinal System
 *
 */
public class DownloadableSubmission extends Submission {

	@Expose protected String downloadUrl;
	@Expose protected String mirrorDownloads[];
	@Expose protected int downloads = -1, downloadsToday = -1;

	public DownloadableSubmission(String url) {
		super(url);
	}

	/**
	 * Gets the download URL for this submission.
	 * 
	 * @return the download URL.
	 */
	public String getDownload() {
		if (downloadUrl == null)
			throw new UnloadedResourceExcpetion(url, "submissionDownload");
		return downloadUrl;
	}

	/**
	 * Gets any alternative download URLs for this project.
	 * 
	 * @return the download URLs.
	 */
	public String[] getMirrorDownloads() {
		if (mirrorDownloads == null)
			throw new UnloadedResourceExcpetion(url, "submissionMirrors");
		return mirrorDownloads;
	}

	@Override
	public Submission load(SubmissionLoader loader) throws IOException {
		super.load(loader);
		this.downloadUrl = ((DownloadableSubmission) loader.getSubmission(url)).getDownload();
		this.mirrorDownloads = ((DownloadableSubmission) loader.getSubmission(url)).getMirrorDownloads();
		return this;
	}
}
