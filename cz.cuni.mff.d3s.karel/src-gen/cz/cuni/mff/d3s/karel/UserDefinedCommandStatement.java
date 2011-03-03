/**
 * <copyright>
 * </copyright>
 *
 */
package cz.cuni.mff.d3s.karel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>User Defined Command Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cz.cuni.mff.d3s.karel.UserDefinedCommandStatement#getCommand <em>Command</em>}</li>
 * </ul>
 * </p>
 *
 * @see cz.cuni.mff.d3s.karel.KarelPackage#getUserDefinedCommandStatement()
 * @model
 * @generated
 */
public interface UserDefinedCommandStatement extends Statement
{
  /**
   * Returns the value of the '<em><b>Command</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Command</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Command</em>' reference.
   * @see #setCommand(UserDefinedCommand)
   * @see cz.cuni.mff.d3s.karel.KarelPackage#getUserDefinedCommandStatement_Command()
   * @model
   * @generated
   */
  UserDefinedCommand getCommand();

  /**
   * Sets the value of the '{@link cz.cuni.mff.d3s.karel.UserDefinedCommandStatement#getCommand <em>Command</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Command</em>' reference.
   * @see #getCommand()
   * @generated
   */
  void setCommand(UserDefinedCommand value);

} // UserDefinedCommandStatement
