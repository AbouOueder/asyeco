/**
 * UNCTAD
 * ASYCUDA PROJECT
 * 
 */ 
package un.asyeco.eco;



import un.adtcommons.FinderStatusColorSchema;

/**
 * F_Color is a FinderStatusColorSchema for Color of Certificate Document
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class F_Color extends FinderStatusColorSchema implements C_eCO {

	public static F_Color INSTANCE = new F_Color();
	
	protected StatusColors[] getNormalStatusColors(){
		return new StatusColors[]{
			new StatusColors(ST_CREATED, prelodgedColor, Black),
			new StatusColors(ST_SUBMITTED, registeredColor, Black),	
			new StatusColors(ST_ACCEPTED, validatedColor, Black),	
			new StatusColors(ST_REJECTED, cancelledColor, Black),	
			new StatusColors(ST_VALIDATED_CUSTOMS, customsValidatedColor, Black),	
			//new StatusColors(ST_ISSUED, exitedColor, Black),	
			new StatusColors(ST_CO_SENT, sentColor, Black),	
			//new StatusColors(ST_REVOKED, cancelledColor, Black),	
			
			//new StatusColors(ST_VALIDATED, assessedColor, Black),	
			//new StatusColors(ST_INSPECTED, Violet, Black)	
		};
	}	
	
	protected StatusColors[] getSelectedStatusColors(){
		return new StatusColors[]{
			new StatusColors(ST_CREATED, selectedColor, Yellow),	
			new StatusColors(ST_SUBMITTED, selectedColor, Yellow),
			new StatusColors(ST_ACCEPTED, selectedColor, Yellow),		
			new StatusColors(ST_REJECTED, selectedColor, Yellow),	
			new StatusColors(ST_VALIDATED_CUSTOMS, selectedColor, Yellow),	
			new StatusColors(ST_CO_SENT, selectedColor, Yellow),	
			//new StatusColors(ST_VALIDATED, selectedColor, Yellow),	
			//new StatusColors(ST_INSPECTED, selectedColor, Yellow)	
		};
	}	

	
}