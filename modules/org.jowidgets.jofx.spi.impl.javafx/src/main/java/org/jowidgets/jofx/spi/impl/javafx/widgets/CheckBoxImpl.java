/*
 * Copyright (c) 2012, dabaukne
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

/*
 * Copyright (c) 2010, Michael Grossmann
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * * Neither the name of the jo-widgets.org nor the
 * names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH
 * DAMAGE.
 */
package org.jowidgets.jofx.spi.impl.javafx.widgets;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.CheckBox;

import org.jowidgets.common.types.Dimension;
import org.jowidgets.common.types.Markup;
import org.jowidgets.jofx.spi.impl.javafx.util.FontProvider;
import org.jowidgets.spi.widgets.ICheckBoxSpi;
import org.jowidgets.spi.widgets.setup.ICheckBoxSetupSpi;

public class CheckBoxImpl extends AbstractInputControl implements ICheckBoxSpi {

	public CheckBoxImpl(final ICheckBoxSetupSpi setup) {
		super(new CheckBox());

		setText(setup.getText());
		setToolTipText(setup.getToolTipText());
		setMarkup(setup.getMarkup());

		getUiReference().selectedProperty().addListener(new InvalidationListener() {

			@Override
			public void invalidated(final Observable observable) {
				fireInputChanged(getUiReference().isSelected());

			}
		});

		//TODO DB Style with CSS
		//getUiReference().setBackground(null);
		//avoid that checkbox have a border on the left or right side
		//getUiReference().setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));
	}

	@Override
	public CheckBox getUiReference() {
		return (CheckBox) super.getUiReference();
	}

	@Override
	public void setEditable(final boolean editable) {
		getUiReference().setDisable(!editable);
	}

	@Override
	public void setMarkup(final Markup markup) {
		final CheckBox checkBox = getUiReference();
		checkBox.setFont(FontProvider.deriveFont(checkBox.getFont(), markup));
	}

	@Override
	public void setFontSize(final int size) {
		getUiReference().setFont(FontProvider.deriveFont(getUiReference().getFont(), size));
	}

	@Override
	public void setFontName(final String fontName) {
		getUiReference().setFont(FontProvider.deriveFont(getUiReference().getFont(), fontName));
	}

	@Override
	public void setText(final String text) {
		getUiReference().setText(text);
	}

	@Override
	public boolean isSelected() {
		return getUiReference().isSelected();
	}

	@Override
	public void setSelected(final boolean selected) {
		getUiReference().setSelected(selected);
	}

	@Override
	public Dimension getMinSize() {
		return getPreferredSize();
	}

	@Override
	public Dimension getMaxSize() {
		return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
	}
}
