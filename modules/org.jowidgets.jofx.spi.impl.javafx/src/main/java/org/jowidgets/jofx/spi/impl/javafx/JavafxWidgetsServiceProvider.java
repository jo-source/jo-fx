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

package org.jowidgets.jofx.spi.impl.javafx;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.stage.Window;

import org.jowidgets.common.application.IApplicationRunner;
import org.jowidgets.common.image.IImageRegistry;
import org.jowidgets.common.threads.IUiThreadAccessCommon;
import org.jowidgets.common.types.Position;
import org.jowidgets.common.widgets.IComponentCommon;
import org.jowidgets.jofx.spi.impl.javafx.application.JavafxApplicationRunner;
import org.jowidgets.jofx.spi.impl.javafx.image.JavafxImageHandleFactorySpi;
import org.jowidgets.jofx.spi.impl.javafx.image.JavafxImageRegistry;
import org.jowidgets.jofx.spi.impl.javafx.threads.JavafxThreadAccess;
import org.jowidgets.spi.IOptionalWidgetsFactorySpi;
import org.jowidgets.spi.IWidgetFactorySpi;
import org.jowidgets.spi.IWidgetsServiceProvider;
import org.jowidgets.spi.image.IImageHandleFactorySpi;

public class JavafxWidgetsServiceProvider implements IWidgetsServiceProvider {

	private final JavafxImageHandleFactorySpi imageHandleFactorySpi;
	private final JavafxWidgetFactory widgetFactory;
	private final JavafxOptionalWidgetsFactory optionalWidgetsFactory;
	private final JavafxImageRegistry imageRegistry;

	public JavafxWidgetsServiceProvider() {
		super();
		this.imageRegistry = JavafxImageRegistry.getInstance();
		this.imageHandleFactorySpi = new JavafxImageHandleFactorySpi(imageRegistry);
		this.widgetFactory = new JavafxWidgetFactory();
		this.optionalWidgetsFactory = new JavafxOptionalWidgetsFactory();
	}

	@Override
	public IImageRegistry getImageRegistry() {
		return JavafxImageRegistry.getInstance();
	}

	@Override
	public IImageHandleFactorySpi getImageHandleFactory() {
		return imageHandleFactorySpi;
	}

	@Override
	public IWidgetFactorySpi getWidgetFactory() {
		return widgetFactory;
	}

	@Override
	public IOptionalWidgetsFactorySpi getOptionalWidgetFactory() {
		return optionalWidgetsFactory;
	}

	@Override
	public IUiThreadAccessCommon createUiThreadAccess() {
		return new JavafxThreadAccess();
	}

	@Override
	public IApplicationRunner createApplicationRunner() {
		return new JavafxApplicationRunner();
	}

	//TODO DB change this if JavaFX provides a better way
	@Override
	public Object getActiveWindowUiReference() {
		for (@SuppressWarnings("deprecation")
		final Iterator<Window> windowsIterator = Stage.impl_getWindows(); windowsIterator.hasNext();) {
			final Window window = windowsIterator.next();
			if (window.isShowing() && window instanceof Stage) {
				return window;
			}
		}
		return null;
	}

	//TODO DB change this if JavaFX provides a better way
	@Override
	public List<Object> getAllWindowsUiReference() {
		final List<Object> result = new LinkedList<Object>();
		for (@SuppressWarnings("deprecation")
		final Iterator<Window> windowsIterator = Stage.impl_getWindows(); windowsIterator.hasNext();) {
			final Window window = windowsIterator.next();
			if (window instanceof Stage) {
				result.add(window);
			}
		}
		return result;
	}

	@Override
	public Position toScreen(final Position localPosition, final IComponentCommon component) {
		final Point2D point = ((Node) component.getUiReference()).localToParent(localPosition.getX(), localPosition.getY());
		return new Position((int) point.getX(), (int) point.getY());
	}

	@Override
	public Position toLocal(final Position screenPosition, final IComponentCommon component) {
		throw new UnsupportedOperationException();
	}
}
