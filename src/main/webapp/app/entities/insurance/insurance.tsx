import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './insurance.reducer';

export const Insurance = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const insuranceList = useAppSelector(state => state.insurance.entities);
  const loading = useAppSelector(state => state.insurance.loading);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        sort: `${sortState.sort},${sortState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?sort=${sortState.sort},${sortState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [sortState.order, sortState.sort]);

  const sort = p => () => {
    setSortState({
      ...sortState,
      order: sortState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = sortState.sort;
    const order = sortState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    } else {
      return order === ASC ? faSortUp : faSortDown;
    }
  };

  return (
    <div>
      <h2 id="insurance-heading" data-cy="InsuranceHeading">
        <Translate contentKey="wayThereJhipsterApp.insurance.home.title">Insurances</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="wayThereJhipsterApp.insurance.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/insurance/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="wayThereJhipsterApp.insurance.home.createLabel">Create new Insurance</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {insuranceList && insuranceList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="wayThereJhipsterApp.insurance.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('insID')}>
                  <Translate contentKey="wayThereJhipsterApp.insurance.insID">Ins ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('insID')} />
                </th>
                <th className="hand" onClick={sort('insDescription')}>
                  <Translate contentKey="wayThereJhipsterApp.insurance.insDescription">Ins Description</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('insDescription')} />
                </th>
                <th className="hand" onClick={sort('startDate')}>
                  <Translate contentKey="wayThereJhipsterApp.insurance.startDate">Start Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('startDate')} />
                </th>
                <th className="hand" onClick={sort('expirationDate')}>
                  <Translate contentKey="wayThereJhipsterApp.insurance.expirationDate">Expiration Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('expirationDate')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {insuranceList.map((insurance, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/insurance/${insurance.id}`} color="link" size="sm">
                      {insurance.id}
                    </Button>
                  </td>
                  <td>{insurance.insID}</td>
                  <td>{insurance.insDescription}</td>
                  <td>
                    {insurance.startDate ? <TextFormat type="date" value={insurance.startDate} format={APP_LOCAL_DATE_FORMAT} /> : null}
                  </td>
                  <td>
                    {insurance.expirationDate ? (
                      <TextFormat type="date" value={insurance.expirationDate} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/insurance/${insurance.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/insurance/${insurance.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/insurance/${insurance.id}/delete`)}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="wayThereJhipsterApp.insurance.home.notFound">No Insurances found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Insurance;
