import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Insurance from './insurance';
import InsuranceDetail from './insurance-detail';
import InsuranceUpdate from './insurance-update';
import InsuranceDeleteDialog from './insurance-delete-dialog';

const InsuranceRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Insurance />} />
    <Route path="new" element={<InsuranceUpdate />} />
    <Route path=":id">
      <Route index element={<InsuranceDetail />} />
      <Route path="edit" element={<InsuranceUpdate />} />
      <Route path="delete" element={<InsuranceDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default InsuranceRoutes;
