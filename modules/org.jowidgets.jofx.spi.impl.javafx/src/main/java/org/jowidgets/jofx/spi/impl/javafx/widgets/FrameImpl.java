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

import java.util.Collection;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.jowidgets.common.types.Dimension;
import org.jowidgets.common.widgets.IButtonCommon;
import org.jowidgets.common.widgets.IControlCommon;
import org.jowidgets.common.widgets.descriptor.IWidgetDescriptor;
import org.jowidgets.common.widgets.factory.ICustomWidgetCreator;
import org.jowidgets.common.widgets.factory.IGenericWidgetFactory;
import org.jowidgets.common.widgets.layout.ILayoutDescriptor;
import org.jowidgets.jofx.spi.impl.javafx.image.JavafxImageRegistry;
import org.jowidgets.spi.widgets.IFrameSpi;
import org.jowidgets.spi.widgets.IMenuBarSpi;
import org.jowidgets.spi.widgets.setup.IFrameSetupSpi;

public class FrameImpl extends JavafxWindow implements IFrameSpi {

	public FrameImpl(final IGenericWidgetFactory factory, final IFrameSetupSpi setup) {
		super(factory, new Stage(), setup.isCloseable());
		getUiReference().setTitle(setup.getTitle());
		getUiReference().setResizable(setup.isResizable());
		if (setup.getIcon() != null) {
			getUiReference().getIcons().add(JavafxImageRegistry.getInstance().getImageHandle(setup.getIcon()).getImage());
		}
	}

	public FrameImpl(final IGenericWidgetFactory factory, final Stage stage) {
		super(factory, stage, true);
	}

	@Override
	public Stage getUiReference() {
		return super.getUiReference();
	}

	@Override
	public IMenuBarSpi createMenuBar() {
		//TODO DB VBox workaround could pose a problem
		final MenuBar bar = new MenuBar();
		final Parent oldRoot = getUiReference().getScene().getRoot();
		final VBox newRoot = new VBox();
		newRoot.getChildren().addAll(bar, oldRoot);
		getUiReference().getScene().setRoot(newRoot);
		return new MenuBarImpl(bar);
	}

	@Override
	public void setDefaultButton(final IButtonCommon button) {
		((Button) button.getUiReference()).setDefaultButton(true);
	}

	@Override
	public <WIDGET_TYPE extends IControlCommon> WIDGET_TYPE add(
		final Integer index,
		final IWidgetDescriptor<? extends WIDGET_TYPE> descriptor,
		final Object layoutConstraints) {
		final WIDGET_TYPE widget = getContainerDelegate().add(index, descriptor, layoutConstraints);
		return widget;
	}

	@Override
	public <WIDGET_TYPE extends IControlCommon> WIDGET_TYPE add(
		final Integer index,
		final ICustomWidgetCreator<WIDGET_TYPE> creator,
		final Object layoutConstraints) {
		final WIDGET_TYPE widget = getContainerDelegate().add(index, creator, layoutConstraints);
		return widget;
	}

	@Override
	public boolean remove(final IControlCommon control) {
		return getContainerDelegate().remove(control);
	}

	@Override
	public void setTabOrder(final Collection<? extends IControlCommon> tabOrder) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setLayout(final ILayoutDescriptor layoutDescriptor) {
		getContainerDelegate().setLayout(layoutDescriptor);
	}

	@Override
	public void layoutBegin() {}

	@Override
	public void layoutEnd() {
		getContainerDelegate().layoutEnd();
	}

	@Override
	public void removeAll() {
		getContainerDelegate().removeAll();
	}

	@Override
	public void setMinSize(final Dimension minSize) {
		getUiReference().setMinHeight(minSize.getHeight());
		getUiReference().setMinWidth(minSize.getWidth());
	}

	@Override
	public void setTitle(final String title) {
		getUiReference().setTitle(title);
	}

}
