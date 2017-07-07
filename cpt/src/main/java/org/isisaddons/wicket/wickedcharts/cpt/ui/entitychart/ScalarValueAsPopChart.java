/*
 *  Copyright 2013~2014 Dan Haywood
 *
 *  Licensed under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.isisaddons.wicket.wickedcharts.cpt.ui.entitychart;

import com.googlecode.wickedcharts.highcharts.options.Options;
import com.googlecode.wickedcharts.wicket6.highcharts.Chart;

import org.apache.isis.core.metamodel.adapter.ObjectAdapter;
import org.apache.isis.viewer.wicket.model.models.ScalarModel;
import org.apache.isis.viewer.wicket.ui.panels.PanelAbstract;

import org.isisaddons.wicket.wickedcharts.cpt.applib.PopChart;

public class ScalarValueAsPopChart extends PanelAbstract<ScalarModel> {

    private static final long serialVersionUID = 1L;

    public ScalarValueAsPopChart(final String id, final ScalarModel scalarModel) {
        super(id, scalarModel);

        buildGui();
    }

    private void buildGui() {

        final ScalarModel model = getModel();
        final ObjectAdapter chartAdapter = model.getObject();
        final Object chartObj = chartAdapter.getObject();
        PopChart chart = (PopChart) chartObj;
        Options options = chart.getOptions();
        
        addOrReplace(new Chart("popchart", options));
    }
    
    @Override
    protected void onModelChanged() {
        buildGui();
    }
}
