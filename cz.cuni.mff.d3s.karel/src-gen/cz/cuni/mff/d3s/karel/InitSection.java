/**
 * <copyright>
 * </copyright>
 *
 */
package cz.cuni.mff.d3s.karel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Init Section</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cz.cuni.mff.d3s.karel.InitSection#getInitCommands <em>Init Commands</em>}</li>
 * </ul>
 * </p>
 *
 * @see cz.cuni.mff.d3s.karel.KarelPackage#getInitSection()
 * @model
 * @generated
 */
public interface InitSection extends EObject
{
  /**
   * Returns the value of the '<em><b>Init Commands</b></em>' containment reference list.
   * The list contents are of type {@link cz.cuni.mff.d3s.karel.InitCommand}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Init Commands</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Init Commands</em>' containment reference list.
   * @see cz.cuni.mff.d3s.karel.KarelPackage#getInitSection_InitCommands()
   * @model containment="true"
   * @generated
   */
  EList<InitCommand> getInitCommands();

} // InitSection
