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

package com.radixdlt.cli

import picocli.CommandLine


class Composite {
    static class Encrypted {
        @CommandLine.Option(names = ["-k", "--keystore"], paramLabel = "KEYSTORE", description = "location of keystore file.", required = true)
        String keyStore

        @CommandLine.Option(names = ["-p", "--password"], paramLabel = "PASSWORD", description = "password", required = true)
        String password
    }

    static class IdentityInfo {
        @CommandLine.ArgGroup(exclusive = false)
        Encrypted encrypted

        @CommandLine.Option(names = ["-u", "--unencryptedkeyfile"], paramLabel = "UNENCRYPTED_KEYFILE", description = "location of unencrypted keyfile.")
        String unencryptedKeyFile
    }
}
