import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './rate.reducer';

export const Rate = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const rateList = useAppSelector(state => state.rate.entities);
  const loading = useAppSelector(state => state.rate.loading);

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
      <h2 id="rate-heading" data-cy="RateHeading">
        <Translate contentKey="wayThereJhipsterApp.rate.home.title">Rates</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="wayThereJhipsterApp.rate.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/rate/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="wayThereJhipsterApp.rate.home.createLabel">Create new Rate</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {rateList && rateList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="wayThereJhipsterApp.rate.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('rateID')}>
                  <Translate contentKey="wayThereJhipsterApp.rate.rateID">Rate ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rateID')} />
                </th>
                <th className="hand" onClick={sort('timeOfDay')}>
                  <Translate contentKey="wayThereJhipsterApp.rate.timeOfDay">Time Of Day</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('timeOfDay')} />
                </th>
                <th className="hand" onClick={sort('vehicleSize')}>
                  <Translate contentKey="wayThereJhipsterApp.rate.vehicleSize">Vehicle Size</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('vehicleSize')} />
                </th>
                <th className="hand" onClick={sort('baseRate')}>
                  <Translate contentKey="wayThereJhipsterApp.rate.baseRate">Base Rate</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('baseRate')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {rateList.map((rate, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/rate/${rate.id}`} color="link" size="sm">
                      {rate.id}
                    </Button>
                  </td>
                  <td>{rate.rateID}</td>
                  <td>{rate.timeOfDay}</td>
                  <td>{rate.vehicleSize}</td>
                  <td>{rate.baseRate}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/rate/${rate.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/rate/${rate.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/rate/${rate.id}/delete`)}
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
              <Translate contentKey="wayThereJhipsterApp.rate.home.notFound">No Rates found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Rate;
