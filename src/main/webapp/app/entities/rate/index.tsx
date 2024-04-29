import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Rate from './rate';
import RateDetail from './rate-detail';
import RateUpdate from './rate-update';
import RateDeleteDialog from './rate-delete-dialog';

const RateRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Rate />} />
    <Route path="new" element={<RateUpdate />} />
    <Route path=":id">
      <Route index element={<RateDetail />} />
      <Route path="edit" element={<RateUpdate />} />
      <Route path="delete" element={<RateDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default RateRoutes;
