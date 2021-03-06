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
package org.jowidgets.jofx.spi.impl.javafx.util;

import javafx.geometry.Pos;
import javafx.scene.text.TextAlignment;

import org.jowidgets.common.types.AlignmentHorizontal;
import org.jowidgets.util.Assert;

public final class AlignmentConvert {

	private AlignmentConvert() {};

	public static TextAlignment convertTextAlignment(final AlignmentHorizontal alignmentHorizontal) {
		Assert.paramNotNull(alignmentHorizontal, "alignmentHorizontal");

		if (AlignmentHorizontal.RIGHT.equals(alignmentHorizontal)) {
			return TextAlignment.RIGHT;
		}
		else if (AlignmentHorizontal.LEFT.equals(alignmentHorizontal)) {
			return TextAlignment.LEFT;
		}
		else if (AlignmentHorizontal.CENTER.equals(alignmentHorizontal)) {
			return TextAlignment.CENTER;
		}
		else {
			throw new IllegalArgumentException("Alignment '" + alignmentHorizontal + "' is unknown");
		}
	}

	public static Pos convertPosAlignment(final AlignmentHorizontal alignmentHorizontal) {
		Assert.paramNotNull(alignmentHorizontal, "alignmentHorizontal");

		if (AlignmentHorizontal.RIGHT.equals(alignmentHorizontal)) {
			return Pos.CENTER_RIGHT;
		}
		else if (AlignmentHorizontal.LEFT.equals(alignmentHorizontal)) {
			return Pos.CENTER_LEFT;
		}
		else if (AlignmentHorizontal.CENTER.equals(alignmentHorizontal)) {
			return Pos.CENTER;
		}
		else {
			throw new IllegalArgumentException("Alignment '" + alignmentHorizontal + "' is unknown");
		}
	}

}
