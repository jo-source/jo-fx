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

import javafx.scene.Node;

import org.jowidgets.common.color.IColorConstant;
import org.jowidgets.common.types.Markup;
import org.jowidgets.jofx.spi.impl.javafx.util.ColorCSSConverter;

public final class StyleDelegate {
	private String fontColorCSS = "";
	private String backgroundColorCSS = "";
	private String fontSizeCSS = "";
	private String fontNameCSS = "";
	private String borderCSS = "";
	private String markupCSS = "";
	private final Node node;

	public StyleDelegate(final Node node) {
		this.node = node;
	}

	public void setForegroundColor(final IColorConstant colorValue) {
		final String colorToCSS = ColorCSSConverter.colorToCSS(colorValue);
		if (!colorToCSS.isEmpty()) {
			fontColorCSS = "-fx-text-fill: #" + colorToCSS + ";\n";
			setStyle();
		}
	}

	public void setBackgroundColor(final IColorConstant colorValue) {
		final String colorToCSS = ColorCSSConverter.colorToCSS(colorValue);
		if (!colorToCSS.isEmpty()) {
			backgroundColorCSS = "-fx-background-color: #" + colorToCSS + ";\n";
			borderCSS = "-fx-border-color: #d0d0d0;\n";
			setStyle();
		}
	}

	public void setMarkup(final Markup newMarkup) {
		if (Markup.DEFAULT.equals(newMarkup)) {
			markupCSS = "-fx-font-style: normal;\n -fx-font-weight: normal;\n";
		}
		else if (Markup.STRONG.equals(newMarkup)) {
			markupCSS = "-fx-font-style: normal;\n -fx-font-weight: bold;\n";
		}
		else if (Markup.EMPHASIZED.equals(newMarkup)) {
			markupCSS = "-fx-font-style: italic;\n -fx-font-weight: normal;\n";
		}

		setStyle();
	}

	public IColorConstant getForegroundColor() {
		return ColorCSSConverter.cssToColor(fontColorCSS);
	}

	public IColorConstant getBackgroundColor() {
		return ColorCSSConverter.cssToColor(backgroundColorCSS);
	}

	public void setFontSize(final int size) {
		fontSizeCSS = "-fx-font-size: " + size * 100 / 72 + "pt;\n";
		setStyle();
	}

	public void setFontName(final String fontName) {
		fontNameCSS = "-fx-font-family: " + fontName + ";\n";
		setStyle();
	}

	public void setNoBorder() {
		if (backgroundColorCSS.isEmpty()) {
			borderCSS = "-fx-border-color: #ffffff;\n" + "-fx-border-insets: 0;\n" + "-fx-border-width: 0;\n";
		}
		else {
			borderCSS = "-fx-border-color: null;\n" + "-fx-border-insets: 0;\n" + "-fx-border-width: 0;\n";
		}
		setStyle();
	}

	public void setStyle() {
		node.setStyle(fontNameCSS + fontSizeCSS + borderCSS + backgroundColorCSS + fontColorCSS + markupCSS);
	}

}
