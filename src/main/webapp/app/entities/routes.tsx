import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Customer from './customer';
import Driver from './driver';
import Vehicle from './vehicle';
import Location from './location';
import Insurance from './insurance';
import Payment from './payment';
import Rate from './rate';
import Reservation from './reservation';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="customer/*" element={<Customer />} />
        <Route path="driver/*" element={<Driver />} />
        <Route path="vehicle/*" element={<Vehicle />} />
        <Route path="location/*" element={<Location />} />
        <Route path="insurance/*" element={<Insurance />} />
        <Route path="payment/*" element={<Payment />} />
        <Route path="rate/*" element={<Rate />} />
        <Route path="reservation/*" element={<Reservation />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
