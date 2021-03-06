/*
 * Copyright (c) 2012, David Bauknecht
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

import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCharacterCombination;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

import org.jowidgets.common.image.IImageConstant;
import org.jowidgets.common.types.Accelerator;
import org.jowidgets.jofx.spi.impl.javafx.image.JavafxImageRegistry;
import org.jowidgets.jofx.spi.impl.javafx.util.ModifierConvert;
import org.jowidgets.jofx.spi.impl.javafx.util.VirtualKeyConvert;
import org.jowidgets.spi.widgets.IMenuItemSpi;

public class MenuItemImpl implements IMenuItemSpi {

	private char mnemonic;
	private String oldText;
	private final MenuItem menuItem;

	public MenuItemImpl() {
		this(new MenuItem());
	}

	public MenuItemImpl(final MenuItem menuItem) {
		this.menuItem = menuItem;
		getUiReference().setMnemonicParsing(true);
	}

	@Override
	public MenuItem getUiReference() {
		return menuItem;
	}

	@Override
	public void setIcon(final IImageConstant icon) {
		if (icon != null) {
			getUiReference().setGraphic(JavafxImageRegistry.getInstance().getImage(icon));
		}
		else {
			getUiReference().setGraphic(null);
		}
	}

	@Override
	public void setText(final String text) {
		if (oldText != null && text.equals(oldText)) {
			getUiReference().setText(assignMnemonictoText(text));
		}
		else {
			oldText = text;
			getUiReference().setText(text);
		}
	}

	private String assignMnemonictoText(final String text) {
		if (mnemonic != Character.UNASSIGNED) {
			final char[] charArray = text.toCharArray();
			for (int i = 0; i < charArray.length; i++) {
				if (charArray[i] == mnemonic || Character.toLowerCase(charArray[i]) == mnemonic) {
					return text.replaceFirst("" + charArray[i], "_" + charArray[i]);
				}
			}
		}
		return text;
	}

	@Override
	public void setToolTipText(final String text) {
		// TODO DB Auto-generated method stub
	}

	@Override
	public void setMnemonic(final char mnemonic) {
		this.mnemonic = mnemonic;
		setText(oldText);
	}

	public void setAccelerator(final Accelerator accelerator) {
		getUiReference().setAccelerator(getKeyCodeCombination(accelerator));
	}

	private KeyCombination getKeyCodeCombination(final Accelerator accelerator) {
		final Character character = accelerator.getCharacter();
		if (character != null) {
			return new KeyCharacterCombination(character.toString(), ModifierConvert.convert(accelerator.getModifier()));
		}
		else {
			return new KeyCodeCombination(
				VirtualKeyConvert.convert(accelerator.getVirtualKey()),
				ModifierConvert.convert(accelerator.getModifier()));
		}

	}

	@Override
	public void setEnabled(final boolean enabled) {
		getUiReference().setDisable(!enabled);
	}

	@Override
	public boolean isEnabled() {
		return !(getUiReference().isDisable());
	}
}
