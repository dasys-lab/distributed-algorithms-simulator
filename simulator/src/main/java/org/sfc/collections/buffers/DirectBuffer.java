/*
 * Copyright (c) 2007 Thomas Weise for sigoa
 * Simple Interface for Global Optimization Algorithms
 * http://www.sigoa.org/
 * 
 * E-Mail           : info@sigoa.org
 * Creation Date    : 2007-11-14
 * Creator          : Thomas Weise
 * Original Filename: org.sfc.collections.buffers.DirectBuffer.java
 * Last modification: 2007-11-14
 *                by: Thomas Weise
 * 
 * License          : GNU LESSER GENERAL PUBLIC LICENSE
 *                    Version 2.1, February 1999
 *                    You should have received a copy of this license along
 *                    with this library; if not, write to theFree Software
 *                    Foundation, Inc. 51 Franklin Street, Fifth Floor,
 *                    Boston, MA 02110-1301, USA or download the license
 *                    under http://www.gnu.org/licenses/lgpl.html or
 *                    http://www.gnu.org/copyleft/lesser.html.
 *                    
 * Warranty         : This software is provided "as is" without any
 *                    warranty; without even the implied warranty of
 *                    merchantability or fitness for a particular purpose.
 *                    See the Gnu Lesser General Public License for more
 *                    details.
 */

package org.sfc.collections.buffers;

/**
 * The direct buffer
 * 
 * @param <OT>
 *          the object type
 * @author Thomas Weise
 */
public abstract class DirectBuffer<OT extends DirectBufferable<OT>>
    extends BasicBuffer<OT> {
  /**
   * the serial version uid
   */
  private static final long serialVersionUID = 1;

  /**
   * the buffer
   */
  OT m_buffer;

  /**
   * Allocate a new object
   * 
   * @return the new object
   */
  @Override
  public OT allocate() {
    OT x;

    x = this.m_buffer;
    if (x != null) {
      this.m_buffer = x.m_next;
      x.m_next = null;
      return x;
    }

    return this.create();
  }

  /**
   * Dispose an object
   * 
   * @param object
   *          the object to be disposed
   */
  @Override
  public void dispose(final OT object) {
    object.m_next = this.m_buffer;
    this.m_buffer = object;
  }

  /**
   * Dispose all objects
   * 
   * @param object
   *          the object to be disposed
   */
  public void disposeAll(final OT object) {
    OT m, n;

    if (object == null)
      return;

    m = object;
    for (n = m.m_next; n != null; n = n.m_next) {
      m = n;
    }

    m.m_next = this.m_buffer;
    this.m_buffer = object;
  }
}
