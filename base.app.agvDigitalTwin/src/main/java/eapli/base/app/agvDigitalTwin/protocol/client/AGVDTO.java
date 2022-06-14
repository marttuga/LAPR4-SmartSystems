/*
 * Copyright (c) 2013-2022 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eapli.base.app.agvDigitalTwin.protocol.client;

import eapli.base.warehousemanagement.domain.Status;

/**
 * @author Paulo Gandra de Sousa 2021.05.25
 */
@SuppressWarnings("java:S1104")
public class AGVDTO {

    public AGVDTO(final long id, final String dock, final int autonomy, final String description, final String model,
                  final Status status ) {
        this.id = id;
        this.agv_dock = dock;
        this.agv_description = description;
        this.agv_autonomy = autonomy;
        this.agv_model = model;
        this.agv_status = status;
    }

    public Long id;

    public String agv_dock;
    public String agv_description;
    public int agv_autonomy;
    public String agv_model;
    public Status agv_status;
}
