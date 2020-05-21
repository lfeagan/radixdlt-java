/*
 * (C) Copyright 2020 Radix DLT Ltd
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the “Software”),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

package com.radixdlt.client.application.translate.data;

import java.util.List;
import java.util.stream.Collectors;

import com.radixdlt.client.application.identity.RadixIdentity;
import com.radixdlt.client.application.translate.AtomToExecutedActionsMapper;
import com.radixdlt.client.atommodel.cru.CRUDataParticle;
import com.radixdlt.client.core.atoms.Atom;
import com.radixdlt.client.core.atoms.particles.SpunParticle;

import io.reactivex.Observable;

/**
 * Maps an atom to some number of CRUDataUpdate
 */
public class AtomToCRUDataUpdateMapper implements AtomToExecutedActionsMapper<CRUDataUpdate> {

	@Override
	public Class<CRUDataUpdate> actionClass() {
		return CRUDataUpdate.class;
	}

	@Override
	public Observable<CRUDataUpdate> map(Atom atom, RadixIdentity identity) {
        long timestamp = atom.getTimestamp();
        List<CRUDataUpdate> dataUpdates = atom.spunParticles()
                .map(SpunParticle::getParticle)
                .filter(p -> p instanceof CRUDataParticle)
                .map(CRUDataParticle.class::cast)
                .map(p -> new CRUDataUpdate(p.rri(), p.data(), timestamp, p.euid()))
                .collect(Collectors.toList());
        return Observable.fromIterable(dataUpdates);
	}

}
