/*
Copyright (c) 2004-2005, Dennis M. Sosnoski
All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice, this
   list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice,
   this list of conditions and the following disclaimer in the documentation
   and/or other materials provided with the distribution.
 * Neither the name of JiBX nor the names of its contributors may be used
   to endorse or promote products derived from this software without specific
   prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package org.jibx.binding.model;

/**
 * Model component for <b>split</b> element of binding definition. This is only
 * used for structuring purposes, and is eliminated when the binding definition
 * is split into specialized input and output versions.
 *
 * @author Dennis M. Sosnoski
 * @version 1.0
 */
 
public class SplitElement extends NestingElementBase
{
    /** Input side of binding (<code>null</code> if none). */
    private InputElement m_inputSide;
    
    /** Output side of binding (<code>null</code> if none). */
    private OutputElement m_outputSide;
    
    /**
     * Constructor.
     */
    public SplitElement() {
        super(SPLIT_ELEMENT);
    }
    
    /**
     * Get input side of binding.
     * 
     * @return input side (<code>null</code> if none)
     */
    public InputElement getInputSide() {
        return m_inputSide;
    }
    
    /**
     * Set input side of binding.
     * 
     * @param input element containing input binding definition
     * (<code>null</code> if none)
     */
    public void setInputSide(InputElement input) {
        m_inputSide = input;
    }
    
    /**
     * Get output side of binding.
     * 
     * @return output side (<code>null</code> if none)
     */
    public OutputElement getOutputSide() {
        return m_outputSide;
    }
    
    /**
     * Set output side of binding.
     * 
     * @param output element containing output binding definition
     * (<code>null</code> if none)
     */
    public void setOutputSide(OutputElement output) {
        m_outputSide = output;
    }
}