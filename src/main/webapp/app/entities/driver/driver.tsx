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

import { getEntities } from './driver.reducer';

export const Driver = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const driverList = useAppSelector(state => state.driver.entities);
  const loading = useAppSelector(state => state.driver.loading);

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
      <h2 id="driver-heading" data-cy="DriverHeading">
        <Translate contentKey="wayThereJhipsterApp.driver.home.title">Drivers</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="wayThereJhipsterApp.driver.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/driver/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="wayThereJhipsterApp.driver.home.createLabel">Create new Driver</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {driverList && driverList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="wayThereJhipsterApp.driver.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('driverID')}>
                  <Translate contentKey="wayThereJhipsterApp.driver.driverID">Driver ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('driverID')} />
                </th>
                <th className="hand" onClick={sort('fname')}>
                  <Translate contentKey="wayThereJhipsterApp.driver.fname">Fname</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('fname')} />
                </th>
                <th className="hand" onClick={sort('lname')}>
                  <Translate contentKey="wayThereJhipsterApp.driver.lname">Lname</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('lname')} />
                </th>
                <th className="hand" onClick={sort('dob')}>
                  <Translate contentKey="wayThereJhipsterApp.driver.dob">Dob</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dob')} />
                </th>
                <th>
                  <Translate contentKey="wayThereJhipsterApp.driver.vehicle">Vehicle</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {driverList.map((driver, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/driver/${driver.id}`} color="link" size="sm">
                      {driver.id}
                    </Button>
                  </td>
                  <td>{driver.driverID}</td>
                  <td>{driver.fname}</td>
                  <td>{driver.lname}</td>
                  <td>{driver.dob ? <TextFormat type="date" value={driver.dob} format={APP_LOCAL_DATE_FORMAT} /> : null}</td>
                  <td>{driver.vehicle ? <Link to={`/vehicle/${driver.vehicle.id}`}>{driver.vehicle.id}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/driver/${driver.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/driver/${driver.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/driver/${driver.id}/delete`)}
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
              <Translate contentKey="wayThereJhipsterApp.driver.home.notFound">No Drivers found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Driver;
