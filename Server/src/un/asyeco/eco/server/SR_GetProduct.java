package un.asyeco.eco.server; 
import so.kernel.core.DataSet;
import so.kernel.core.interfaces.KDocumentInterface;
import so.kernel.server.rules.KSR_DocumentRead;
import un.asyeco.product.C_Product;
import static un.asyeco.product.C_Product.*;

/**
 * 
 * <p>
 * This Class retrieve the product related to the code filled in CO document to show the list of products on certificate page. 
 * <p>
 * @author <A HREF="mailto:oueder_aboubakr@gmail.com">Aboubacar Ouedraogo</A>
 */
public class SR_GetProduct extends KSR_DocumentRead  {

	public SR_GetProduct(S_eCO binder) {
	//	super(binder, KSR_DocumentRead.ERROR | KSR_DocumentRead.DOCUMENT_FOUND | KSR_DocumentRead.NON_KEYS_DATA, "Problem opening Product Doc",
	//			"Product_Finder", 
	//			new Object[] { C_Product.ST_ACCEPTED, C_Product.ST_CREATED });
		
		super(binder, KSR_DocumentRead.ERROR | KSR_DocumentRead.DOCUMENT_FOUND | KSR_DocumentRead.NON_KEYS_DATA, "", 
				new Object[] { C_Product.ST_ACCEPTED, C_Product.ST_CREATED });

	}

	protected void prepareBeforeRead(DataSet source, DataSet key) {
		key.seg(CMP).seg(PROD).add(COD); 
		key.ds(CMP).ds(PROD).de(COD).copyFrom(source.ds(CMP).ds(PROD).de(COD));
	}

	protected DataSet prepareAfterRead(DataSet source) {     
		return source.ds(KDocumentInterface.NORMAL_ID);
	}
}
