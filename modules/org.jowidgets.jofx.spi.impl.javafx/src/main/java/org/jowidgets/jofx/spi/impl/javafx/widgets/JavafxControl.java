/*
 * Copyright (c) 2012, David Bauknecht
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 * * Neither the name of the jo-widgets.org nor the
 *   names of its contributors may be used to endorse or promote products
 *   derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL jo-widgets.org BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH
 * DAMAGE.
 */

package org.jowidgets.jofx.spi.impl.javafx.widgets;

import javafx.scene.control.Control;
import javafx.scene.control.Tooltip;

import org.jowidgets.common.color.IColorConstant;
import org.jowidgets.common.types.Cursor;
import org.jowidgets.common.types.Dimension;
import org.jowidgets.common.types.Position;
import org.jowidgets.common.widgets.controller.IComponentListener;
import org.jowidgets.common.widgets.controller.IFocusListener;
import org.jowidgets.common.widgets.controller.IKeyListener;
import org.jowidgets.common.widgets.controller.IMouseListener;
import org.jowidgets.common.widgets.controller.IMouseMotionListener;
import org.jowidgets.common.widgets.controller.IPopupDetectionListener;
import org.jowidgets.jofx.spi.impl.javafx.util.CursorConvert;
import org.jowidgets.spi.impl.controller.ActionObservable;
import org.jowidgets.spi.impl.controller.PopupDetectionObservable;
import org.jowidgets.spi.widgets.IControlSpi;
import org.jowidgets.spi.widgets.IPopupMenuSpi;
import org.jowidgets.util.Assert;

public class JavafxControl extends ActionObservable implements IControlSpi {

	private final JavafxComponent componentDelegate;

	public JavafxControl(final Control control) {
		this(control, true);
	}

	public JavafxControl(final Control control, final boolean triggerPopupDetection) {
		componentDelegate = new JavafxComponent(control, triggerPopupDetection);
	}

	@Override
	public Control getUiReference() {
		return (Control) componentDelegate.getUiReference();
	}

	@Override
	public IPopupMenuSpi createPopupMenu() {
		return componentDelegate.createPopupMenu();
	}

	@Override
	public void setEnabled(final boolean enabled) {
		getUiReference().setDisable(!(enabled));
	}

	@Override
	public boolean isEnabled() {
		return (!getUiReference().isDisabled());
	}

	@Override
	public void redraw() {
		componentDelegate.redraw();
	}

	@Override
	public void setRedrawEnabled(final boolean enabled) {
		componentDelegate.setRedrawEnabled(enabled);
	}

	@Override
	public boolean requestFocus() {
		getUiReference().requestFocus();
		return getUiReference().isFocused();

	}

	@Override
	public void setForegroundColor(final IColorConstant colorValue) {
		componentDelegate.setForegroundColor(colorValue);
	}

	@Override
	public void setBackgroundColor(final IColorConstant colorValue) {
		componentDelegate.setBackgroundColor(colorValue);
	}

	@Override
	public IColorConstant getForegroundColor() {
		return componentDelegate.getForegroundColor();
	}

	@Override
	public IColorConstant getBackgroundColor() {
		return componentDelegate.getBackgroundColor();
	}

	@Override
	public void setCursor(final Cursor cursor) {
		getUiReference().setCursor(CursorConvert.convert(cursor));
	}

	@Override
	public void setVisible(final boolean visible) {
		getUiReference().setVisible(visible);
	}

	@Override
	public boolean isVisible() {
		return getUiReference().isVisible();
	}

	@Override
	public Dimension getSize() {
		return new Dimension(
			(int) getUiReference().getLayoutBounds().getWidth(),
			(int) getUiReference().getLayoutBounds().getHeight());
	}

	@Override
	public void setSize(final Dimension size) {
		Assert.paramNotNull(size, "size");
		getUiReference().resize(size.getWidth(), size.getHeight());
	}

	@Override
	public Position getPosition() {
		return new Position(
			(int) getUiReference().getLayoutBounds().getMinX(),
			(int) getUiReference().getLayoutBounds().getMinY());
	}

	@Override
	public void setPosition(final Position position) {
		getUiReference().relocate(position.getX(), position.getY());
	}

	@Override
	public void addComponentListener(final IComponentListener componentListener) {
		componentDelegate.addComponentListener(componentListener);
	}

	@Override
	public void removeComponentListener(final IComponentListener componentListener) {
		componentDelegate.removeComponentListener(componentListener);
	}

	@Override
	public void addFocusListener(final IFocusListener listener) {
		componentDelegate.addFocusListener(listener);
	}

	@Override
	public void removeFocusListener(final IFocusListener listener) {
		componentDelegate.removeFocusListener(listener);
	}

	@Override
	public void addKeyListener(final IKeyListener listener) {
		componentDelegate.addKeyListener(listener);
	}

	@Override
	public void removeKeyListener(final IKeyListener listener) {
		componentDelegate.removeKeyListener(listener);
	}

	@Override
	public void addMouseListener(final IMouseListener listener) {
		componentDelegate.addMouseListener(listener);
	}

	@Override
	public void removeMouseListener(final IMouseListener listener) {
		componentDelegate.removeMouseListener(listener);
	}

	@Override
	public void addMouseMotionListener(final IMouseMotionListener arg0) {
		componentDelegate.addMouseMotionListener(arg0);
	}

	@Override
	public void removeMouseMotionListener(final IMouseMotionListener arg0) {
		componentDelegate.removeMouseMotionListener(arg0);
	}

	@Override
	public void addPopupDetectionListener(final IPopupDetectionListener listener) {
		componentDelegate.addPopupDetectionListener(listener);

	}

	@Override
	public void removePopupDetectionListener(final IPopupDetectionListener listener) {
		componentDelegate.removePopupDetectionListener(listener);
	}

	@Override
	public void setToolTipText(final String toolTip) {
		final Tooltip tool = new Tooltip(toolTip);
		if (toolTip == null || toolTip.isEmpty()) {
			Tooltip.uninstall(getUiReference(), tool);
		}
		else {
			Tooltip.install(getUiReference(), tool);
		}
	}

	@Override
	public void setLayoutConstraints(final Object layoutConstraints) {
		getUiReference().setUserData(layoutConstraints);
	}

	@Override
	public Object getLayoutConstraints() {
		return getUiReference().getUserData();
	}

	@Override
	public Dimension getMinSize() {
		return new Dimension((int) getUiReference().minWidth(Control.USE_COMPUTED_SIZE), (int) getUiReference().minHeight(
				Control.USE_COMPUTED_SIZE));
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension((int) getUiReference().prefWidth(Control.USE_COMPUTED_SIZE), (int) getUiReference().prefHeight(
				Control.USE_COMPUTED_SIZE));
	}

	@Override
	public Dimension getMaxSize() {
		return new Dimension((int) getUiReference().maxWidth(Control.USE_COMPUTED_SIZE), (int) getUiReference().maxHeight(
				Control.USE_COMPUTED_SIZE));
	}

	protected PopupDetectionObservable getPopupDetectionObservable() {
		return componentDelegate.getPopupDetectionObservable();
	}

}
