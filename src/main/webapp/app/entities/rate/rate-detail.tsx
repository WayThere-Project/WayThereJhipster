import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './rate.reducer';

export const RateDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const rateEntity = useAppSelector(state => state.rate.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="rateDetailsHeading">
          <Translate contentKey="wayThereJhipsterApp.rate.detail.title">Rate</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{rateEntity.id}</dd>
          <dt>
            <span id="rateID">
              <Translate contentKey="wayThereJhipsterApp.rate.rateID">Rate ID</Translate>
            </span>
          </dt>
          <dd>{rateEntity.rateID}</dd>
          <dt>
            <span id="timeOfDay">
              <Translate contentKey="wayThereJhipsterApp.rate.timeOfDay">Time Of Day</Translate>
            </span>
          </dt>
          <dd>{rateEntity.timeOfDay}</dd>
          <dt>
            <span id="vehicleSize">
              <Translate contentKey="wayThereJhipsterApp.rate.vehicleSize">Vehicle Size</Translate>
            </span>
          </dt>
          <dd>{rateEntity.vehicleSize}</dd>
          <dt>
            <span id="baseRate">
              <Translate contentKey="wayThereJhipsterApp.rate.baseRate">Base Rate</Translate>
            </span>
          </dt>
          <dd>{rateEntity.baseRate}</dd>
        </dl>
        <Button tag={Link} to="/rate" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/rate/${rateEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default RateDetail;
