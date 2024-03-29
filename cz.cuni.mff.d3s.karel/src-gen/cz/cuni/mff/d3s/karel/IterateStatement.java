/**
 * <copyright>
 * </copyright>
 *
 */
package cz.cuni.mff.d3s.karel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Iterate Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cz.cuni.mff.d3s.karel.IterateStatement#getTimes <em>Times</em>}</li>
 *   <li>{@link cz.cuni.mff.d3s.karel.IterateStatement#getStatements <em>Statements</em>}</li>
 * </ul>
 * </p>
 *
 * @see cz.cuni.mff.d3s.karel.KarelPackage#getIterateStatement()
 * @model
 * @generated
 */
public interface IterateStatement extends Statement
{
  /**
   * Returns the value of the '<em><b>Times</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Times</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Times</em>' attribute.
   * @see #setTimes(int)
   * @see cz.cuni.mff.d3s.karel.KarelPackage#getIterateStatement_Times()
   * @model
   * @generated
   */
  int getTimes();

  /**
   * Sets the value of the '{@link cz.cuni.mff.d3s.karel.IterateStatement#getTimes <em>Times</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Times</em>' attribute.
   * @see #getTimes()
   * @generated
   */
  void setTimes(int value);

  /**
   * Returns the value of the '<em><b>Statements</b></em>' containment reference list.
   * The list contents are of type {@link cz.cuni.mff.d3s.karel.Statement}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Statements</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Statements</em>' containment reference list.
   * @see cz.cuni.mff.d3s.karel.KarelPackage#getIterateStatement_Statements()
   * @model containment="true"
   * @generated
   */
  EList<Statement> getStatements();

} // IterateStatement
