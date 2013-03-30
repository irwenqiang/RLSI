/****************************************************************************************
 *Project: Regularized Latent Semantic Indexing 1.0
Reference: Quan Wang, Jun Xu, Hang Li, and Nick Craswell. Regularized latent semantic indexing. SIGIR 2011: 685-694. 
 ****************************************************************************************
 *Laboratory of Intelligent Information Processing, Nankai University
 *Authors: Zhicheng He, Yingjie Xu, Jun Xu, MaoQiang xie, Yalou Huang
 *****************************************************************************************/
package nkiip.rlsi.matrix;

import java.util.LinkedList;

import nkiip.rlsi.basicStruct.KeyValuePair;

public class SparseMatrixByRow {

	public LinkedList<KeyValuePair>[] p_Rows = null;
	public int m_NumRow;
	public int m_NumColumn;

	/**
	 * constructs a SparseMatrixByRow with specified number of rows and columns
	 * @param row number of rows
	 * @param col number of columns
	 */
	public SparseMatrixByRow(int row, int col) {
		p_Rows = new LinkedList[row];
		for (int i = 0; i < row; i++) {
			p_Rows[i] = new LinkedList<KeyValuePair>();
		}
		m_NumRow = row;
		m_NumColumn = col;
	}

	/**
	 * get method for matrix row
	 * @param rowid row number
	 * @return the matrix row
	 * @throws Exception if out of index bound
	 */
	public LinkedList<KeyValuePair> GetRow(int rowid) throws Exception {
		if (rowid >= m_NumRow || p_Rows == null || p_Rows[rowid] == null) {
			throw new Exception("rowid out of bound");
		}
		return p_Rows[rowid];
	}

	/**
	 * set method for matrix row
	 * @param rowid row number
	 * @param lstRow new matrix row
	 * @return succeed or not
	 */
	public boolean SetRow(int rowid, LinkedList<KeyValuePair> lstRow) {
		if (rowid >= m_NumRow) {
			return false;
		}
		p_Rows[rowid] = lstRow;
		return true;
	}

	/**
	 * set method for matrix entry
	 * @param rowid row number
	 * @param colid column number
	 * @param value new value
	 * @return succeed or not
	 */
	public boolean AddValue(int rowid, int colid, float value) {
		if (rowid >= m_NumRow || colid >= m_NumColumn)
			return false;

		p_Rows[rowid].add(new KeyValuePair(colid, value));
		return true;
	}

	/**
	 * set all of the entries zero
	 */
	public void Zero() {
		for (int i = 0; i < m_NumRow; i++) {
			p_Rows[i].clear();
		}
	}
	
	/**
	 * Release all of the memory
	 */
	public void ReleaseMemory() {
		p_Rows = null;
		m_NumRow = 0;
		m_NumColumn = 0;

	}
}
