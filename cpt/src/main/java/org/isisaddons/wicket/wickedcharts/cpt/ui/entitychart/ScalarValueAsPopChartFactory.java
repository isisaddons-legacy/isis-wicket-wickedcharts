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

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;

import org.apache.isis.core.metamodel.spec.ObjectSpecification;
import org.apache.isis.core.metamodel.specloader.SpecificationLoader;
import org.apache.isis.core.runtime.system.context.IsisContext;
import org.apache.isis.viewer.wicket.model.models.ScalarModel;
import org.apache.isis.viewer.wicket.ui.ComponentFactory;
import org.apache.isis.viewer.wicket.ui.ComponentFactoryAbstract;
import org.apache.isis.viewer.wicket.ui.ComponentType;

import org.isisaddons.wicket.wickedcharts.cpt.applib.PopChart;

/**
 * {@link ComponentFactory} for {@link ScalarValueAsPopChart}.
 */
public class ScalarValueAsPopChartFactory extends ComponentFactoryAbstract {

    private static final long serialVersionUID = 1L;

    public ScalarValueAsPopChartFactory() {
        super(ComponentType.SCALAR_NAME_AND_VALUE);
    }

    @Override
    public ApplicationAdvice appliesTo(final IModel<?> model) {
        
        if (!(model instanceof ScalarModel)) {
            return ApplicationAdvice.DOES_NOT_APPLY;
        }
        final ScalarModel valueModel = (ScalarModel) model;
        if(model.getObject() == null) {
            return ApplicationAdvice.DOES_NOT_APPLY;
        }
        
        final ObjectSpecification chartOptionsSpec = getSpecificationLoader().loadSpecification(PopChart.class);
        final ObjectSpecification scalarSpec = valueModel.getObject().getSpecification();
        
        return appliesExclusivelyIf(scalarSpec.isOfType(chartOptionsSpec));
    }

    @Override
    public Component createComponent(final String id, final IModel<?> scalarModel) {
        return new ScalarValueAsPopChart(id, (ScalarModel)scalarModel);
    }

    protected SpecificationLoader getSpecificationLoader() {
        return IsisContext.getSessionFactory().getSpecificationLoader();
    }
}
