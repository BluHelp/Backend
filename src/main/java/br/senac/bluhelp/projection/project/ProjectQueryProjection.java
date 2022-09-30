package br.senac.bluhelp.projection.project;

import br.senac.bluhelp.enumeration.progress.Progress;

public interface ProjectQueryProjection {
	
	Long getId();
	
	String getTitle();
	
	byte[] getPhoto();
	
	Progress getProgress();
	
	Double getAverageReview();
}
