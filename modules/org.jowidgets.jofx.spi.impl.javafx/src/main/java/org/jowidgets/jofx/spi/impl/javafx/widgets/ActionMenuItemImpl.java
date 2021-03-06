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

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

import org.jowidgets.common.widgets.controller.IActionListener;
import org.jowidgets.spi.impl.controller.ActionObservable;
import org.jowidgets.spi.widgets.IActionMenuItemSpi;

public class ActionMenuItemImpl extends MenuItemImpl implements IActionMenuItemSpi {

	private final ActionObservable actionObservable;

	public ActionMenuItemImpl() {
		this(new MenuItem());
	}

	public ActionMenuItemImpl(final MenuItem menuItem) {
		super(menuItem);
		this.actionObservable = new ActionObservable();

		getUiReference().addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

			@Override
			public void handle(final ActionEvent event) {
				actionObservable.fireActionPerformed();
			}
		});

	}

	@Override
	public void addActionListener(final IActionListener actionListener) {
		actionObservable.addActionListener(actionListener);
	}

	@Override
	public void removeActionListener(final IActionListener actionListener) {
		actionObservable.removeActionListener(actionListener);
	}

}
