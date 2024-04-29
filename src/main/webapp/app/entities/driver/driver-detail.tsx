import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './driver.reducer';

export const DriverDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const driverEntity = useAppSelector(state => state.driver.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="driverDetailsHeading">
          <Translate contentKey="wayThereJhipsterApp.driver.detail.title">Driver</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{driverEntity.id}</dd>
          <dt>
            <span id="driverID">
              <Translate contentKey="wayThereJhipsterApp.driver.driverID">Driver ID</Translate>
            </span>
          </dt>
          <dd>{driverEntity.driverID}</dd>
          <dt>
            <span id="fname">
              <Translate contentKey="wayThereJhipsterApp.driver.fname">Fname</Translate>
            </span>
          </dt>
          <dd>{driverEntity.fname}</dd>
          <dt>
            <span id="lname">
              <Translate contentKey="wayThereJhipsterApp.driver.lname">Lname</Translate>
            </span>
          </dt>
          <dd>{driverEntity.lname}</dd>
          <dt>
            <span id="dob">
              <Translate contentKey="wayThereJhipsterApp.driver.dob">Dob</Translate>
            </span>
          </dt>
          <dd>{driverEntity.dob ? <TextFormat value={driverEntity.dob} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}</dd>
          <dt>
            <Translate contentKey="wayThereJhipsterApp.driver.vehicle">Vehicle</Translate>
          </dt>
          <dd>{driverEntity.vehicle ? driverEntity.vehicle.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/driver" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/driver/${driverEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DriverDetail;
