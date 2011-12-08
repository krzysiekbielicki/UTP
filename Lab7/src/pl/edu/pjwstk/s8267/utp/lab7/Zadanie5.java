package pl.edu.pjwstk.s8267.utp.lab7;

public class Zadanie5 {
	@interface Bind {
		String author();
		String date();
		int currentRevision() default 1;
		String lastModified() default "N/A";
		String lastModifiedBy() default "N/A";

		String[] reviewers(); // Note use of array
	}
}
