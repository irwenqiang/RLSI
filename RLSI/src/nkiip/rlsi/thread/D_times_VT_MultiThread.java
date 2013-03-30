/****************************************************************************************
 *Project: Regularized Latent Semantic Indexing 1.0
Reference: Quan Wang, Jun Xu, Hang Li, and Nick Craswell. Regularized latent semantic indexing. SIGIR 2011: 685-694. 
 ****************************************************************************************
 *Laboratory of Intelligent Information Processing, Nankai University
 *Authors: Zhicheng He, Yingjie Xu, Jun Xu, MaoQiang xie, Yalou Huang
 *****************************************************************************************/
package nkiip.rlsi.thread;

import nkiip.rlsi.basicStruct.DocID_TFIDF;
import nkiip.rlsi.matrix.DenseMatrix;
import nkiip.rlsi.matrix.Term_Doc_tfidf_Matrix;

public class D_times_VT_MultiThread {
	public static int iMaxThreads = 4;
	public int iThreadID = -1;
	public Term_Doc_tfidf_Matrix D; // term * doc
	public DenseMatrix V; // doc * topic
	public DenseMatrix R;

	/**
	 * constructs a D_times_VT_MultiThread
	 * @param id thread id
	 * @param inD term_document matrix
	 * @param inV document_topic matrix
	 * @param inR R matrix to store calculation results
	 */
	public D_times_VT_MultiThread(int id, Term_Doc_tfidf_Matrix inD,
			DenseMatrix inV, DenseMatrix inR) {
		iThreadID = id;
		D = inD;
		V = inV;
		R = inR;
	}

	/**
	 * calculates the specified part of D_times_VT
	 * @throws Exception if calculation fails
	 */
	public void Matrix_Mul_ThreadI() throws Exception {
		// for each terms
		for (int iTerm = 0; iTerm < D.iNumberOfTerms; iTerm++) {
			if (iTerm % iMaxThreads != iThreadID)
				continue;

			DocID_TFIDF[] lstDocTfIDF = D.GetRow(iTerm);
			// for each topics
			for (int iTopic = 0; iTopic < V.m_NumColumn; iTopic++) {
				R.SetValue(iTerm, iTopic, 0);
				for (int i = 0; i < lstDocTfIDF.length; i++) {
					DocID_TFIDF pDocTfIDF = lstDocTfIDF[i];
					R.SetValue(iTerm, iTopic,
							R.GetValue(iTerm, iTopic) + pDocTfIDF.getTFIDF()
									* V.GetValue(pDocTfIDF.getDocID(), iTopic));
				}
			}
		}
	}
}
