/**
 * <copyright>
 * </copyright>
 *
 */
package cz.cuni.mff.d3s.karel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Init Heading Command</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cz.cuni.mff.d3s.karel.InitHeadingCommand#getHeading <em>Heading</em>}</li>
 * </ul>
 * </p>
 *
 * @see cz.cuni.mff.d3s.karel.KarelPackage#getInitHeadingCommand()
 * @model
 * @generated
 */
public interface InitHeadingCommand extends InitCommand
{
  /**
   * Returns the value of the '<em><b>Heading</b></em>' attribute.
   * The literals are from the enumeration {@link cz.cuni.mff.d3s.karel.HeadingKind}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Heading</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Heading</em>' attribute.
   * @see cz.cuni.mff.d3s.karel.HeadingKind
   * @see #setHeading(HeadingKind)
   * @see cz.cuni.mff.d3s.karel.KarelPackage#getInitHeadingCommand_Heading()
   * @model
   * @generated
   */
  HeadingKind getHeading();

  /**
   * Sets the value of the '{@link cz.cuni.mff.d3s.karel.InitHeadingCommand#getHeading <em>Heading</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Heading</em>' attribute.
   * @see cz.cuni.mff.d3s.karel.HeadingKind
   * @see #getHeading()
   * @generated
   */
  void setHeading(HeadingKind value);

} // InitHeadingCommand
