/**
 * <copyright>
 * </copyright>
 *
 */
package cz.cuni.mff.d3s.karel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Init Position Command</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cz.cuni.mff.d3s.karel.InitPositionCommand#getX <em>X</em>}</li>
 *   <li>{@link cz.cuni.mff.d3s.karel.InitPositionCommand#getY <em>Y</em>}</li>
 * </ul>
 * </p>
 *
 * @see cz.cuni.mff.d3s.karel.KarelPackage#getInitPositionCommand()
 * @model
 * @generated
 */
public interface InitPositionCommand extends InitCommand
{
  /**
   * Returns the value of the '<em><b>X</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>X</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>X</em>' attribute.
   * @see #setX(int)
   * @see cz.cuni.mff.d3s.karel.KarelPackage#getInitPositionCommand_X()
   * @model
   * @generated
   */
  int getX();

  /**
   * Sets the value of the '{@link cz.cuni.mff.d3s.karel.InitPositionCommand#getX <em>X</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>X</em>' attribute.
   * @see #getX()
   * @generated
   */
  void setX(int value);

  /**
   * Returns the value of the '<em><b>Y</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Y</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Y</em>' attribute.
   * @see #setY(int)
   * @see cz.cuni.mff.d3s.karel.KarelPackage#getInitPositionCommand_Y()
   * @model
   * @generated
   */
  int getY();

  /**
   * Sets the value of the '{@link cz.cuni.mff.d3s.karel.InitPositionCommand#getY <em>Y</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Y</em>' attribute.
   * @see #getY()
   * @generated
   */
  void setY(int value);

} // InitPositionCommand
