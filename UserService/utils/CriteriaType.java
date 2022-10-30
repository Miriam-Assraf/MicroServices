package acs.utils;

public enum CriteriaType {
	BY_LAST_NAME("byLastName"),
	BY_MINIMUM_AGE("byMinimumAge"),
	BY_ROLE("byRole");
	
	private final String criteria;
	
	CriteriaType(final String criteria){
		this.criteria=criteria;
	}
	
	 @Override
	 public String toString() {
		 return criteria;
	 }
	
}
